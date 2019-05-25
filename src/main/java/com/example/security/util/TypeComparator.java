package com.example.security.util;

import com.example.security.entity.Type;

import java.util.Comparator;

public class TypeComparator implements Comparator<Type> {
    @Override
    public int compare(Type o1, Type o2) {
        return o1.getId()-o2.getId();
    }
}
