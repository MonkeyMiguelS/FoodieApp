package com.domain.usecase.login;

import com.domain.exceptions.ApiGatewayException;
import com.domain.exceptions.NetworkException;
import com.domain.exceptions.errors.InternalUseCaseError;
import com.domain.exceptions.errors.NetworkUseCaseError;
import com.domain.gateway.login.LoginApiGateway;
import com.domain.gateway.session.SessionLocalGateway;
import com.domain.models.AuthModel;
import com.domain.models.SessionModel;
import com.domain.usecase.UseCaseResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginUseCaseTest {

    LoginUseCase loginUseCase;

    @Mock
    LoginApiGateway apiGateway;

    @Mock
    SessionLocalGateway localGateway;

    @Before
    public void setUp() throws Exception {
        loginUseCase = new LoginUseCase(apiGateway, localGateway);
    }

    /**
     * Cuando se ejecuta el caso de uso, se hace login en la API
     *
     * @throws Exception
     */
    @Test
    public void shouldDoAuthWhenCallUseCase() throws Exception {
        loginUseCase.call();
        verify(apiGateway).doAuth(any(AuthModel.class));
    }

    /**
     * Cuando se ejecuta el caso de uso, y el login es correcto,
     * se persiste la sesión.
     *
     * @throws Exception
     */
    @Test
    public void shouldPersistTokenWhenCallUseCase() throws Exception {
        loginUseCase.call();
        verify(localGateway).persist(any(SessionModel.class));
    }

    /**
     * Si el login es correcto, se devuelve el mismo modelo.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnAuthModelWhenCredentialOk() throws Exception{
        AuthModel authModel = new AuthModel("alias","password");
        SessionModel sessionModel = new SessionModel(authModel);

        Mockito
            .doReturn(sessionModel)
            .when(apiGateway).doAuth(authModel);

        Mockito
            .doNothing()
            .when(localGateway).persist(any(SessionModel.class));

        loginUseCase.setArgs(authModel);

        UseCaseResponse<AuthModel> response = loginUseCase.call();
        Assert.assertTrue(response.getResult().equals(authModel));
    }

    /**
     * Cuando se recibe una excepción de Red, entonces se lanza un error de red.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnNetworkError() throws Exception{
        Mockito
            .doThrow(NetworkException.class)
            .when(apiGateway).doAuth(any(AuthModel.class));

        UseCaseResponse<AuthModel> response = loginUseCase.call();
        Assert.assertTrue(response.getError().getClass().equals(NetworkUseCaseError.class));
    }

    /**
     * Cuando se recibe una excepción de Login, se devuelve un LoginUseCaseError.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnLoginError() throws Exception{
        Mockito
                .doThrow(ApiGatewayException.class)
                .when(apiGateway).doAuth(any(AuthModel.class));

        UseCaseResponse<AuthModel> response = loginUseCase.call();
        Assert.assertTrue(response.getError().getClass().equals(LoginUseCaseError.class));
    }

    /**
     * Cuando se recibe una excepción de API, se devuelve un TypeInternalError.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnInternalError() throws Exception{
        Mockito
                .doThrow(ApiGatewayException.class)
                .when(apiGateway).doAuth(any(AuthModel.class));

        UseCaseResponse<AuthModel> response = loginUseCase.call();
        Assert.assertTrue(response.getError().getClass().equals(LoginUseCaseError.class));
    }

    /**
     * Cuando se produce una excepción no controlada, se devuelve un TypeInternalError.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnInternalErrorWhenThrowUnexpectedException() throws Exception{
        Mockito
            .doThrow(Exception.class)
            .when(apiGateway).doAuth(any(AuthModel.class));

        UseCaseResponse<AuthModel> response = loginUseCase.call();
        Assert.assertTrue(response.getError().getClass().equals(InternalUseCaseError.class));
    }
}