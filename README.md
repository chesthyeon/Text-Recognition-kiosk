# Thymeleaf-and-SpringBoot
kiosk :: Thymeleaf and SpringBoot

api 문서샘플
1. Read store-list

GET http://ec2-13-209-14-10.ap-northeast-2.compute.amazonaws.com:8000/api/stores
	
Response:
[
    {
        "id": 1,
        "name": "GS25역삼상록점",
        "latitude": 37.504022687681610,
        "longitude": 127.044016840943810,
        "stickers": [
            {
                "id": 1,
                "name": "피카츄",
                "num": "1"
            }
        ]
    },
    {
        "id": 2,
        "name": "GS25역삼띵동점",
        "latitude": 37.505135994220550,
        "longitude": 127.042473715607740,
        "stickers": []
    },
    {
        "id": 3,
        "name": "세븐일레븐 KT강남점",
        "latitude": 37.504329904510770,
        "longitude": 127.041602418634470,
        "stickers": []
    },
    {
        "id": 4,
        "name": "GS25 강남상록회관점",
        "latitude": 37.504889926223186,
        "longitude": 127.043960801604300,
        "stickers": []
    },
    {
        "id": 5,
        "name": "CU 역삼성보점",
        "latitude": 37.502396052829390,
        "longitude": 127.044866912535580,
        "stickers": []
    },
    {
        "id": 6,
        "name": "GS25 역삼넥스빌점",
        "latitude": 37.501975017511150,
        "longitude": 127.044377535138520,
        "stickers": []
    }
]

2. Read store

GET http://ec2-13-209-14-10.ap-northeast-2.compute.amazonaws.com:8000/api/stores/<store_id>

Response
[
    {
        "name": "피카츄",
        "num": "1",
        "count": 1
    },
    {
        "name": "파이리",
        "num": "2",
        "count": 2
    }
]

3. Create Sticker

POST http://ec2-13-209-14-10.ap-northeast-2.compute.amazonaws.com:8000/api/sticker

Body
{
    "sticker_name" : "파이리",
    "sticker_num" : "2",
    "store_id" : 1
}

Response
{
    "id": 3,
    "name": "파이리",
    "num": "2"
}
