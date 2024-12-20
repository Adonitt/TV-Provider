package org.example.finalproject.admin.infrastructure.mapping;

public interface SimpleMapper<TEntity, TDto> {
    TEntity toEntity(TDto dto);

    TDto toDto(TEntity entity);
}
