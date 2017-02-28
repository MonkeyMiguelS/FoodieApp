package com.data.repository.local.session.models.mappers;

import com.data.repository.local.session.models.LocalAuthModel;
import com.domain.mappers.TwoWaysMapper;
import com.domain.models.AuthModel;

import javax.inject.Inject;

public class LocalAuthMapper implements TwoWaysMapper<LocalAuthModel, AuthModel> {

    @Inject
    public LocalAuthMapper() {
    }

    @Override
    public LocalAuthModel inverseMap(AuthModel model) {
        LocalAuthModel authModel = new LocalAuthModel(model.getAlias(), null);
        authModel.token = model.getToken();
        return authModel;
    }

    @Override
    public AuthModel map(LocalAuthModel model) {
        AuthModel authModel = new AuthModel(model.getAlias(), null);
        authModel.setToken(model.getToken());
        return authModel;
    }
}
