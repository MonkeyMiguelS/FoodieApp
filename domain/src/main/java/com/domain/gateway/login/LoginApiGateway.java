package com.domain.gateway.login;


import com.domain.exceptions.ApiGatewayException;
import com.domain.exceptions.NetworkException;
import com.domain.models.AuthModel;
import com.domain.models.SessionModel;

public interface LoginApiGateway {

    SessionModel doAuth(AuthModel authModel) throws ApiGatewayException, NetworkException;

}
