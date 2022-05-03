package com.kiosk.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ItemDto {

    private Long id;
    private String itemNm;
    private String itemDetail;
    private int price;
    private String sellStatCd;
    private String foodType;
    private LocalDate regTime;
    private LocalDate updateTime;
}
