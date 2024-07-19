package com.arts.basic.pro.service.base;

import java.util.List;

/**
 * @author
 * @date 2024/5/7
 * @apiNote
 */
public interface BaseMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List <D> toDto(List<E> entityList);
}
