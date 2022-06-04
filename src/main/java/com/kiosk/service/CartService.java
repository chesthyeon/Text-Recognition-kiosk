package com.kiosk.service;

import com.kiosk.dto.CartDetailDto;
import com.kiosk.dto.CartItemDto;
import com.kiosk.entity.*;
import com.kiosk.repository.CartItemRepository;
import com.kiosk.repository.ItemRepository;
import com.kiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;


    public Long addCart(CartItemDto cartItemDto){

        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        CartItem savedCartItem = cartItemRepository.findByItemId(item.getId());

        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }


    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(){

        List<CartDetailDto> cartDetailDtoList;
        cartDetailDtoList = cartItemRepository.findCartDetailDtoList();
        return cartDetailDtoList;
    }


    public void updateCartItemCount(Long cartItemId, int count){
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        cartItem.updateCount(count);
    }

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }
    public void deleteAllCartItem() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        for(CartItem cartItem : cartItems){
            deleteCartItem(cartItem.getId());
        }
    }


}