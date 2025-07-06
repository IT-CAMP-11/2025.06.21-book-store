package com.comarch.szkolenia.book.store.services.impl;

import com.comarch.szkolenia.book.store.services.IIdSequence;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class IdSequence implements IIdSequence {
    int id = 0;

    @Override
    public int getNextId() {
        return ++id;
    }
}
