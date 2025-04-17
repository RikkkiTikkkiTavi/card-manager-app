package com.example.card_manager_app.web.dto.mapper;

import java.util.List;

public interface Mappable<E, D> {

    E fromDto(D dto);

    D toDto(E entity);

    List<D> toDto(List<E> entities);

}
