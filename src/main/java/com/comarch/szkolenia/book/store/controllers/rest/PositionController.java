package com.comarch.szkolenia.book.store.controllers.rest;

import com.comarch.szkolenia.book.store.dao.impl.spring.OrderDAO;
import com.comarch.szkolenia.book.store.model.Position;
import com.comarch.szkolenia.book.store.model.dto.ListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api/v1/position")
public class PositionController {

    private final OrderDAO orderDAO;

    @GetMapping("")
    public ResponseEntity<ListResponseDTO<Position>> getPositionsByOrderId(@RequestParam("orderId") int orderId) {
        return this.orderDAO.findById(orderId)
                .map(o -> ResponseEntity.ok(new ListResponseDTO<>(o.getPositions())))
                .orElse(ResponseEntity.notFound().build());
    }
}
