package com.kiosk.service;

import com.kiosk.dto.OrderDto;
import com.kiosk.entity.Item;
import com.kiosk.entity.Member;
import com.kiosk.repository.ItemRepository;
import com.kiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {


}