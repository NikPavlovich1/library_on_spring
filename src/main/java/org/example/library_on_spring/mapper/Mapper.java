package org.example.library_on_spring.mapper;

public interface Mapper <F, T>{
    T map(F object);

    default T map(F fromObject, T toObject) {
        return toObject;
    }
}
