package com.presentation.modules.login;


import com.domain.commons.logger.Logger;
import com.domain.exceptions.errors.InternalUseCaseError;
import com.domain.exceptions.errors.NetworkUseCaseError;
import com.domain.models.AuthModel;
import com.domain.usecase.login.LoginUseCase;
import com.domain.usecase.login.LoginUseCaseError;
import com.presentation.AppViewInjector;
import com.presentation.Presenter;
import com.presentation.UseCaseExecution;
import com.presentation.UseCaseInvoker;
import com.presentation.UseCaseResult;
import com.presentation.modules.login.validations.AliasInputValidation;
import com.presentation.modules.login.validations.PassInputValidation;

public class LoginPresenter extends Presenter<LoginView> {

    private final UseCaseInvoker useCaseInvoker;
    private final LoginUseCase loginUseCase;
    private final Logger logger;

    public LoginPresenter(UseCaseInvoker useCaseInvoker,
                          AppViewInjector appViewInjector,
                          Logger logger,
                          LoginUseCase loginUseCase) {
        super(appViewInjector);
        this.logger = logger;
        this.useCaseInvoker = useCaseInvoker;
        this.loginUseCase = loginUseCase;
    }

    @Override
    public void onViewAttached() {
        getView().disableButtonLogin();
        cleanSession();
    }

    private void cleanSession(){
        getView().releaseLocalStorage();
    }

    public void validateAliasInput(String inputAlias){
        if (AliasInputValidation.build(inputAlias).isValid())
            getView().enableButtonLogin();
        else
            getView().disableButtonLogin();
    }

    public void validatePassInputs(String inputPass){
        if (PassInputValidation.build(inputPass).isValid())
            getView().enableButtonLogin();
        else
            getView().disableButtonLogin();
    }

    public void loginClicked(final String alias, final String password){
        try {
            getView().showSpinner();
            doLogin(alias, password);
        }catch (Exception ex){
            showInternalError();
            logger.e(ex);
        }
    }

    private void doLogin(final String alias, final String password) {
        loginUseCase.setArgs(new AuthModel(alias,password));

        new UseCaseExecution<>(loginUseCase).result(new UseCaseResult<AuthModel>() {
            @Override
            public void onResult(AuthModel authModel) {
                successLogin(authModel);
            }
        }).error(LoginUseCaseError.class, new UseCaseResult<LoginUseCaseError>() {
            @Override
            public void onResult(LoginUseCaseError error) {
                showLoginError();
            }
        }).error(NetworkUseCaseError.class, new UseCaseResult<NetworkUseCaseError>() {
            @Override
            public void onResult(NetworkUseCaseError error) {
                showNetworkError();
            }
        }).error(InternalUseCaseError.class, new UseCaseResult<InternalUseCaseError>() {
            @Override
            public void onResult(InternalUseCaseError error) {
                showInternalError();
            }
        }).execute(useCaseInvoker);
    }

    private void successLogin(AuthModel authModel){
        getView().hideSpinner();
        getView().loginSuccess();
    }

    private void showLoginError(){
        getView().hideSpinner();
        //// FIXME: 23/2/17 Cambiar de aquí. Sólo para la presentación.
        getView().showLoginError("Usuario no encontrado");
    }

    private void showInternalError(){
        getView().hideSpinner();
        getView().showInternalError();
    }

    private void showNetworkError(){
        getView().hideSpinner();
        getView().showNetworkError();
    }
}