package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class Mapper<E, A>
{
    @Autowired
    ModelMapper mapper;

    private Class<E> entity;
    private Class<A> api;

    Mapper(Class<E> entity, Class<A> api)
    {
        this.entity = entity;
        this.api = api;
    }

    public E toEntity(A api) {
        return Objects.isNull(api)
                ? null
                : mapper.map(api, entity);
    }

    public A toApi(E entity) {
        return mapper.map(entity, api);
    }
}
