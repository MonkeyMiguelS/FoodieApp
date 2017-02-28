package com.presentation.testcommons;

import com.domain.exceptions.errors.InternalUseCaseError;
import com.domain.exceptions.errors.NetworkUseCaseError;
import com.domain.models.AuthModel;
import com.domain.usecase.UseCaseResponse;

public class MockUseCaseResponse {

    public static final UseCaseResponse<AuthModel> ERROR_NETWORK_RESPONSE =
            new UseCaseResponse<>(new NetworkUseCaseError());

    public static final UseCaseResponse<AuthModel> ERROR_INTERNAL_RESPONSE =
            new UseCaseResponse<>(new InternalUseCaseError());
}
