package com.kiosk.repository;

import com.kiosk.dto.ItemSearchDto;
import com.kiosk.dto.MainItemDto;
import com.kiosk.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
//    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable, String param);

}