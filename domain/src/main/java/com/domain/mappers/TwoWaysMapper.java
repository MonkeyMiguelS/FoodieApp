package com.domain.mappers;


public interface TwoWaysMapper<M, P> extends Mapper<M, P> {
    M inverseMap(P model);
}