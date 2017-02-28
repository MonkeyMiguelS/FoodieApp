package com.data.repository.api.login;


import com.data.repository.api.ApiEndPoints;
import com.data.repository.api.login.models.ApiLoginResponseModel;
import com.data.repository.api.login.models.mappers.ApiAuthMapper;
import com.domain.commons.logger.Logger;
import com.domain.exceptions.ApiGatewayException;
import com.domain.exceptions.NetworkException;
import com.domain.gateway.login.LoginApiGateway;
import com.domain.models.AuthModel;
import com.domain.models.SessionModel;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginApiGatewayImpl implements LoginApiGateway {

    private ApiEndPoints apiEndPoints;
    private ApiAuthMapper apiAuthMapper;
    private Logger logger;

    public LoginApiGatewayImpl(Logger logger,
                               ApiEndPoints apiEndPoints,
                               ApiAuthMapper apiAuthMapper) {
        this.apiEndPoints = apiEndPoints;
        this.apiAuthMapper = apiAuthMapper;
        this.logger = logger;
    }

    @Override
    public SessionModel doAuth(AuthModel authModel) throws ApiGatewayException, NetworkException {
        try {
            Call<ApiLoginResponseModel> apiLoginModelCall = apiEndPoints.serviceDoAuth(this.apiAuthMapper.map(authModel));

            Response<ApiLoginResponseModel> response = apiLoginModelCall.execute();

            if (response.isSuccessful()){
                authModel.setToken(response.body().token);
                return new SessionModel(authModel);
            }else if (response.code() >= 400 && response.code() < 500){
                throw new ApiGatewayException();
            }else{
                throw new NetworkException();
            }

        } catch (UnknownHostException | ConnectException | SocketTimeoutException e){
            logger.e(e);
            throw new NetworkException();
        }catch (ApiGatewayException e){
            logger.e(e);
            throw e;
        } catch (Exception e){
            logger.e(e);
            throw new ApiGatewayException();
        }
    }
}
