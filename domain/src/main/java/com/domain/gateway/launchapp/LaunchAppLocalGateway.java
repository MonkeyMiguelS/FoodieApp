package com.domain.gateway.launchapp;


import com.domain.exceptions.LocalGatewayException;
import com.domain.models.LaunchAppModel;

public interface LaunchAppLocalGateway {

    LaunchAppModel obtainLaunchApp() throws LocalGatewayException;

    void persistLaunchApp(LaunchAppModel launchApp) throws LocalGatewayException;

}
