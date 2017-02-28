package com.data.repository.api.login.models.mappers;

import com.data.repository.api.login.models.ApiLoginModel;
import com.domain.mappers.Mapper;
import com.domain.models.AuthModel;

import javax.inject.Inject;

public class ApiAuthMapper implements Mapper<AuthModel, ApiLoginModel> {

    @Inject
    public ApiAuthMapper() { }

    @Override
    public ApiLoginModel map(AuthModel model) {
        return new ApiLoginModel(model.getAlias(), model.getPassword());
    }
}
