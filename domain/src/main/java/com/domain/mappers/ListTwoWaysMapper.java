package com.domain.mappers;


import java.util.ArrayList;
import java.util.List;

public class ListTwoWaysMapper<M, P> implements TwoWaysMapper<List<M>, List<P>> {

    private final TwoWaysMapper<M, P> mapper;

    public ListTwoWaysMapper(TwoWaysMapper<M, P> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<P> map(List<M> models) {
        ArrayList<P> persistences = new ArrayList<>();
        if (models != null && models.size() > 0) {
            for (M model : models) {
                P mapped = mapper.map(model);
                if (mapped != null)
                    persistences.add(mapper.map(model));
            }
        }
        return persistences;
    }

    @Override
    public List<M> inverseMap(List<P> models) {
        ArrayList<M> persistences = new ArrayList<>();
        if (models != null && models.size() > 0){
            for (P model : models){
                M mapped = mapper.inverseMap(model);
                if (mapped != null)
                    persistences.add(mapper.inverseMap(model));
            }
        }
        return persistences;
    }
}