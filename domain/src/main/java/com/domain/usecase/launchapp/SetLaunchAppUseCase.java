package com.domain.usecase.launchapp;


import com.domain.exceptions.errors.InternalUseCaseError;
import com.domain.gateway.launchapp.LaunchAppLocalGateway;
import com.domain.models.LaunchAppModel;
import com.domain.usecase.UseCase;
import com.domain.usecase.UseCaseResponse;

public class SetLaunchAppUseCase implements UseCase<UseCaseResponse<LaunchAppModel>> {

    private LaunchAppModel launchAppModel;
    private LaunchAppLocalGateway launchAppLocalGateway;

    public SetLaunchAppUseCase(LaunchAppLocalGateway launchAppLocalGateway) {
        this.launchAppLocalGateway = launchAppLocalGateway;
    }

    public void setArgs(LaunchAppModel launchAppModel){
        this.launchAppModel = launchAppModel;
    }

    @Override
    public UseCaseResponse<LaunchAppModel> call(){
        try {
            launchAppLocalGateway.persistLaunchApp(launchAppModel);
            return new UseCaseResponse<>(launchAppModel);
        } catch (Exception e) {
            return new UseCaseResponse<>(new InternalUseCaseError());
        }
    }
}
