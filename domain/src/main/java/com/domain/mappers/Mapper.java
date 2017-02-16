package com.domain.mappers;


public interface Mapper<M, P> {
    P map(M model);
}
