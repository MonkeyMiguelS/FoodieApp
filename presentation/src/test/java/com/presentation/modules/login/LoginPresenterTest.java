package com.presentation.modules.login;

import com.domain.commons.logger.Logger;
import com.domain.models.AuthModel;
import com.domain.usecase.UseCaseResponse;
import com.domain.usecase.login.LoginUseCase;
import com.domain.usecase.login.LoginUseCaseError;
import com.presentation.testcommons.FakeUseCaseInvoker;
import com.presentation.testcommons.FakeViewInjector;
import com.presentation.testcommons.MockUseCaseResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    public static String LOGIN_MSG_ERROR = "Usuario no encontrado";

    public static AuthModel authModel = new AuthModel("alias", "pass");

    public static final UseCaseResponse<AuthModel> ERROR_RESPONSE =
            new UseCaseResponse<>(new LoginUseCaseError());

    public static final UseCaseResponse<AuthModel> SUCCESS_RESPONSE =
            new UseCaseResponse<>(authModel);

    @Mock
    LoginUseCase mockLoginUseCase;

    @Mock
    LoginView mockLoginView;

    @Mock
    Logger logger;

    LoginPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(
                FakeUseCaseInvoker.create(),
                new FakeViewInjector(),
                logger,
                mockLoginUseCase);
    }


    /**
     * Nada más iniciarse la vista, se llama al caso de uso encargado de limpiar la sesión.
     *
     * @throws Exception
     */
    @Test
    public void shouldReleaseLocalStorageWhenAttatchView() throws Exception{
        presenter.attachView(mockLoginView);
        verify(mockLoginView).releaseLocalStorage();
    }

    /**
     * Si al hacer click en hacer login se lanza un error, entonces se muestra una notificación
     *
     * @throws Exception
     */
    @Test
    public void shouldShowInternalNotificationWhenThrowUnexpectedException() throws Exception{
        when(mockLoginUseCase.call()).thenThrow(Exception.class);

        presenter.attachView(mockLoginView);
        presenter.loginClicked(authModel.getAlias(), authModel.getPassword());

        verify(mockLoginView).showInternalError();
    }

    /**
     * Cuando llamamos el caso de uso para hacer login, mostramos un spinner.
     *
     * @throws Exception
     */
    @Test
    public void shouldShowSpinnerWhenLoginClicked() throws Exception{
        when(mockLoginUseCase.call()).thenReturn(SUCCESS_RESPONSE);

        presenter.attachView(mockLoginView);
        presenter.loginClicked(authModel.getAlias(), authModel.getPassword());

        verify(mockLoginView).showSpinner();
    }

    /**
     * Cuando se termina el caso de uso con el success del login, se oculta el spinner.
     *
     * @throws Exception
     */
    @Test
    public void shouldHideSpinnerWhenLoginSuccess() throws Exception{
        when(mockLoginUseCase.call()).thenReturn(SUCCESS_RESPONSE);

        presenter.attachView(mockLoginView);
        presenter.loginClicked(authModel.getAlias(), authModel.getPassword());

        verify(mockLoginView, times(1)).hideSpinner();
    }

    /**
     * Cuando se termina el caso de uso con el error del login, se culta el spinner.
     *
     * @throws Exception
     */
    @Test
    public void shouldHideSpinnerWhenLoginError() throws Exception{
        when(mockLoginUseCase.call()).thenReturn(ERROR_RESPONSE);
        presenter.attachView(mockLoginView);
        presenter.loginClicked(authModel.getAlias(), authModel.getPassword());
        verify(mockLoginView).hideSpinner();
    }

    /**
     * Cuando la repuesta del caso de uso es success y se oculta el spinner de espera,
     * se ejecuta el método de "loginSuccess" de la vista.
     *
     * @throws Exception
     */
    @Test
    public void shouldCallLoginSuccess() throws Exception{
        when(mockLoginUseCase.call()).thenReturn(SUCCESS_RESPONSE);

        presenter.attachView(mockLoginView);
        presenter.loginClicked(authModel.getAlias(), authModel.getPassword());

        verify(mockLoginView).hideSpinner();
        verify(mockLoginView).loginSuccess();
    }

    /**
     * Cuando se produce un error en el login, se oculta el spinner y se muestra un mensaje de error
     * que viene del caso de uso.
     *
     * @throws Exception
     */
    @Test
    public void shouldCallLoginError() throws Exception{
        when(mockLoginUseCase.call()).thenReturn(ERROR_RESPONSE);

        presenter.attachView(mockLoginView);
        presenter.loginClicked(authModel.getAlias(), authModel.getPassword());

        verify(mockLoginView).hideSpinner();
        verify(mockLoginView).showLoginError(LOGIN_MSG_ERROR);
    }

    /**
     * Verificación de error de red.
     *
     * @throws Exception
     */
    @Test
    public void shouldShowNetworkError() throws Exception{
        when(mockLoginUseCase.call()).thenReturn(MockUseCaseResponse.ERROR_NETWORK_RESPONSE);

        presenter.attachView(mockLoginView);
        presenter.loginClicked(authModel.getAlias(), authModel.getPassword());

        verify(mockLoginView).showNetworkError();
    }

    /**
     * Verificación Internal Error.
     *
     * @throws Exception
     */
    @Test
    public void shouldShowInternalError() throws Exception{
        when(mockLoginUseCase.call()).thenReturn(MockUseCaseResponse.ERROR_INTERNAL_RESPONSE);

        presenter.attachView(mockLoginView);
        presenter.loginClicked(authModel.getAlias(), authModel.getPassword());

        verify(mockLoginView).showInternalError();
    }
}