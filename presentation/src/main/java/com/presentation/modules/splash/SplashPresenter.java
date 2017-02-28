package com.presentation.modules.splash;


import com.domain.exceptions.errors.InternalUseCaseError;
import com.domain.models.LaunchAppModel;
import com.domain.usecase.launchapp.SetLaunchAppUseCase;
import com.domain.usecase.splash.CheckSplashUseCase;
import com.presentation.AppViewInjector;
import com.presentation.Presenter;
import com.presentation.UseCaseExecution;
import com.presentation.UseCaseInvoker;
import com.presentation.UseCaseResult;

public class SplashPresenter extends Presenter<SplashView> {

    private final UseCaseInvoker useCaseInvoker;
    private final CheckSplashUseCase checkSplashUseCase;
    private final SetLaunchAppUseCase setLaunchAppUseCase;

    public SplashPresenter(UseCaseInvoker useCaseInvoker,
                           AppViewInjector appViewInjector,
                           CheckSplashUseCase checkSplashUseCase,
                           SetLaunchAppUseCase setLaunchAppUseCase) {
        super(appViewInjector);
        this.useCaseInvoker = useCaseInvoker;
        this.checkSplashUseCase = checkSplashUseCase;
        this.setLaunchAppUseCase = setLaunchAppUseCase;
    }

    @Override
    public void onViewAttached() {
        checkFirstTimeLaunchApp();
    }

    public void onFinishSplash(){
        setIsNotFirstStartLaunchApp();
    }

    private void checkFirstTimeLaunchApp() {
        new UseCaseExecution<>(checkSplashUseCase).result(new UseCaseResult<LaunchAppModel>() {
            @Override public void onResult(LaunchAppModel result) {
                if (result.isFirstTime())
                    getView().showSplash();
                else
                    getView().navigateToLogin();
            }
        }).error(InternalUseCaseError.class, new UseCaseResult<InternalUseCaseError>() {
            @Override public void onResult(InternalUseCaseError error) {
                getView().showSplash();
            }
        }).execute(useCaseInvoker);
    }

    private void setIsNotFirstStartLaunchApp(){
        setLaunchAppUseCase.setArgs(new LaunchAppModel(false));

        new UseCaseExecution<>(setLaunchAppUseCase).result(new UseCaseResult<LaunchAppModel>() {
            @Override public void onResult(LaunchAppModel result) {
                getView().navigateToLogin();
            }
        }).error(InternalUseCaseError.class, new UseCaseResult<InternalUseCaseError>() {
            @Override public void onResult(InternalUseCaseError error) {
                getView().navigateToLogin();
            }
        }).execute(useCaseInvoker);
    }
}