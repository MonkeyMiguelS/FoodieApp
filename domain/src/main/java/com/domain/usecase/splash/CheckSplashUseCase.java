package com.domain.usecase.splash;


import com.domain.exceptions.errors.InternalUseCaseError;
import com.domain.gateway.launchapp.LaunchAppLocalGateway;
import com.domain.models.LaunchAppModel;
import com.domain.usecase.UseCase;
import com.domain.usecase.UseCaseResponse;

public class CheckSplashUseCase implements UseCase<UseCaseResponse<LaunchAppModel>> {

    private LaunchAppLocalGateway launchAppLocalGateway;

    public CheckSplashUseCase(LaunchAppLocalGateway launchAppLocalGateway) {
        this.launchAppLocalGateway = launchAppLocalGateway;
    }

    @Override
    public UseCaseResponse<LaunchAppModel> call(){
        try {
            return new UseCaseResponse<>(launchAppLocalGateway.obtainLaunchApp());
        } catch (Exception e) {
            return new UseCaseResponse<>(new InternalUseCaseError());
        }
    }
}
