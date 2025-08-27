package com.comarch.szkolenia.book.store.controllers.rest;

import com.comarch.szkolenia.book.store.dao.impl.spring.UserDAO;
import com.comarch.szkolenia.book.store.model.Order;
import com.comarch.szkolenia.book.store.model.dto.ListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("orderRestController")
@RequestMapping("/rest/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final UserDAO userDAO;

    @GetMapping("")
    public ResponseEntity<ListResponseDTO<Order>> getByUserId(@RequestParam("userId") int userId) {
        return this.userDAO.findById(userId)
                .map(u -> ResponseEntity.ok(new ListResponseDTO<>(u.getOrders())))
                .orElse(ResponseEntity.notFound().build());
    }
}
