package com.comarch.szkolenia.book.store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListResponseDTO<T> {
    private List<T> list;
}
