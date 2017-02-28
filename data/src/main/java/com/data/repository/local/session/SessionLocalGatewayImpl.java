package com.data.repository.local.session;


import com.data.repository.local.LocalStorage;
import com.data.repository.local.session.models.LocalSessionModel;
import com.data.repository.local.session.models.mappers.LocalSessionMapper;
import com.domain.exceptions.LocalGatewayException;
import com.domain.gateway.session.SessionLocalGateway;
import com.domain.models.SessionModel;

import javax.inject.Inject;

public class SessionLocalGatewayImpl implements SessionLocalGateway {

    private final LocalStorage localStorage;
    private final LocalSessionMapper sessionMapper;

    @Inject
    public SessionLocalGatewayImpl(LocalStorage localStorage, LocalSessionMapper sessionMapper) {
        this.localStorage = localStorage;
        this.sessionMapper = sessionMapper;
    }

    @Override
    public void persist(SessionModel sessionModel) throws LocalGatewayException {
        localStorage.persist(sessionMapper.inverseMap(sessionModel));
    }

    @Override
    public SessionModel obtainSessionModel() throws LocalGatewayException {
        return localStorage.findById(LocalSessionModel.class, LocalSessionModel.PK, sessionMapper);
    }

    @Override
    public void clearSession() throws LocalGatewayException {
        localStorage.deleteById(LocalSessionModel.class, LocalSessionModel.PK);
    }

    @Override
    public boolean hasToken() {
        try {
            SessionModel model = localStorage.findById(LocalSessionModel.class, LocalSessionModel.PK, sessionMapper);
            return (model != null && model.getAuthModel() != null && model.getAuthModel().getToken() != null);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public String obtainToken() {
        try {
            SessionModel model = localStorage.findById(LocalSessionModel.class, LocalSessionModel.PK, sessionMapper);
            return model.getAuthModel().getToken();
        }catch (Exception ex){
            return "";
        }
    }
}
