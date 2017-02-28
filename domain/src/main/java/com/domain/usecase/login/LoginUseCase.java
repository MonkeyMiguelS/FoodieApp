package com.domain.usecase.login;


import com.domain.exceptions.ApiGatewayException;
import com.domain.exceptions.NetworkException;
import com.domain.exceptions.errors.InternalUseCaseError;
import com.domain.exceptions.errors.NetworkUseCaseError;
import com.domain.gateway.login.LoginApiGateway;
import com.domain.gateway.session.SessionLocalGateway;
import com.domain.models.AuthModel;
import com.domain.models.SessionModel;
import com.domain.usecase.UseCase;
import com.domain.usecase.UseCaseResponse;

public class LoginUseCase implements UseCase<UseCaseResponse<AuthModel>> {

    private AuthModel authModel;
    private LoginApiGateway loginApiGateway;
    private SessionLocalGateway localGateway;

    public LoginUseCase(LoginApiGateway loginApiGateway, SessionLocalGateway loginLocalGateway) {
        this.loginApiGateway = loginApiGateway;
        this.localGateway = loginLocalGateway;
    }

    public void setArgs(AuthModel authModel){
        this.authModel = authModel;
    }

    @Override
    public UseCaseResponse<AuthModel> call(){
        try {
            SessionModel model = loginApiGateway.doAuth(this.authModel);
            localGateway.persist(model);
            return new UseCaseResponse<>(this.authModel);
        }catch (ApiGatewayException ex){
            return new UseCaseResponse<>(new LoginUseCaseError());
        }catch (NetworkException ex){
            return new UseCaseResponse<>(new NetworkUseCaseError());
        }catch (Exception ex){
            return new UseCaseResponse<>(new InternalUseCaseError());
        }
    }
}
