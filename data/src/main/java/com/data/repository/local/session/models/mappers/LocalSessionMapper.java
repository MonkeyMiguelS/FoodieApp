package com.data.repository.local.session.models.mappers;

import com.data.repository.local.session.models.LocalSessionModel;
import com.domain.mappers.TwoWaysMapper;
import com.domain.models.SessionModel;

import javax.inject.Inject;

public class LocalSessionMapper implements TwoWaysMapper<LocalSessionModel, SessionModel> {

    private LocalAuthMapper authMapper;

    @Inject
    public LocalSessionMapper(LocalAuthMapper localAuthMapper) {
        this.authMapper = localAuthMapper;
    }

    @Override
    public LocalSessionModel inverseMap(SessionModel model) {
        return new LocalSessionModel(authMapper.inverseMap(model.getAuthModel()));
    }

    @Override
    public SessionModel map(LocalSessionModel model) {
        return new SessionModel(authMapper.map(model.getLocalAuthModel()));
    }
}
