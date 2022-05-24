package com.kiosk.service;

import com.kiosk.dto.CartItemDto;
import com.kiosk.entity.CartItem;
import com.kiosk.entity.Item;
import com.kiosk.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OcrService {
    private final ItemRepository itemRepository;
    private final CartService cartService;

    public Map<?, ?> getOcr(String image_url){
        String apiURL = "https://1mte94hdpr.apigw.ntruss.com/custom/v1/15641/df7e3d874c4ebcd0839255e7bb5ecec3d3dc754994d59c437e615e4934d6fdcd/general";
        String secretKey = "UXdxaFVvdVRrSWRVQnRtUFhZVWZpUWZObGxnb05lcHA=";
        HashMap<String, CartItem> hm = new HashMap<>();


        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            con.setRequestProperty("X-OCR-SECRET", secretKey);

            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", "png");     //?  ?  ?  ?   ?  ?
            image.put("url", image_url);    //?  ?   url(S3 경로) ?  ?
            image.put("name", "iamge");     //?  ?  ?   ? ?  ?
            JSONArray images = new JSONArray();
            images.add(image);
            json.put("images", images);
            String postParams = json.toString();

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            //System.out.println(response);

            //json parsing code
            String trans = response.toString();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(trans);
            //System.out.println(jsonObject.get("images"));

            JSONObject jsonMain = (JSONObject) jsonObject;
            JSONArray jsonArr = (JSONArray) jsonMain.get("images");
            if (jsonArr.size() > 0) {
                for (int i = 0; i < jsonArr.size(); i++) {
                    JSONObject answer = (JSONObject) jsonArr.get(i);

                    JSONArray jsonArr2 = (JSONArray) answer.get("fields");
                    HashMap<String, Integer> map = new HashMap<>();//key,value = menu, 개수
                    if (jsonArr2.size() > 0) {
                        String sum = "";
                        for (int j = 0; j < jsonArr2.size(); j++) {
                            JSONObject answer2 = (JSONObject) jsonArr2.get(j);
                            //System.out.print(answer2.get("inferText"));

                            String jsonString = answer2.get("inferText").toString();
                            String s[] = answer2.get("inferText").toString().split("\\D");
                            boolean isLastStr = false;
                            for (String str : s) {
                                if (Integer.parseInt(s[0]) > 0) {
                                    map.put(sum, Integer.parseInt(s[0]));
                                    sum = "";
                                    isLastStr=true;
                                    break;
                                }
                            }

                            if(!isLastStr){
                                sum += jsonString;
                                String linebr = answer2.get("lineBreak").toString();
 /*
                            * 돈까스김밥1개
                               치즈라면4개
                                냉면
                                * 오이
                                => (냉면,1), (오이,1) 로 치환
                            * */
                                if(linebr.equals("true")){
                                    map.put(sum, 1);
                                }
                            }
                        }
                    }
                    for (String s : map.keySet()) {
                        List<Item> itemList = itemRepository.findByItemNm(s);
                        for (Item item : itemList) {
                            CartItemDto cartItemDto = new CartItemDto();
                            cartItemDto.setItemId(item.getId());
                            cartItemDto.setCount(map.get(s));
                            cartService.addCart(cartItemDto);
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hm;
    }
}