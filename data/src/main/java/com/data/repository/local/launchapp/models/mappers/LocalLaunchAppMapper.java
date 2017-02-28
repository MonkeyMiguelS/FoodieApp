package com.data.repository.local.launchapp.models.mappers;

import com.data.repository.local.launchapp.models.LocalLaunchAppModel;
import com.domain.mappers.TwoWaysMapper;
import com.domain.models.LaunchAppModel;

import javax.inject.Inject;

public class LocalLaunchAppMapper implements TwoWaysMapper<LaunchAppModel, LocalLaunchAppModel> {

    @Inject
    public LocalLaunchAppMapper() {

    }

    @Override
    public LaunchAppModel inverseMap(LocalLaunchAppModel model) {
        return new LaunchAppModel(model.isFirstTime());
    }

    @Override
    public LocalLaunchAppModel map(LaunchAppModel model) {
        return new LocalLaunchAppModel(LocalLaunchAppModel.PRIMARY_KEY_CONST, model.isFirstTime);
    }
}
