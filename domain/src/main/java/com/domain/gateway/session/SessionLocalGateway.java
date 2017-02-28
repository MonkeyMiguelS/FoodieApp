package com.domain.gateway.session;


import com.domain.exceptions.LocalGatewayException;
import com.domain.models.SessionModel;

public interface SessionLocalGateway {
    void persist(SessionModel sessionModel) throws LocalGatewayException;
    SessionModel obtainSessionModel() throws LocalGatewayException;
    void clearSession() throws LocalGatewayException;
    boolean hasToken();
    String obtainToken();
}
