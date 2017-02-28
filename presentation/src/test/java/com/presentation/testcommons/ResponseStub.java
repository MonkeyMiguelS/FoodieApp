package com.presentation.testcommons;

import com.domain.exceptions.errors.ApiUseCaseError;
import com.domain.exceptions.errors.InternalUseCaseError;
import com.domain.exceptions.errors.NetworkUseCaseError;
import com.domain.models.ReceiptModel;
import com.domain.usecase.UseCaseResponse;

public class ResponseStub {

    public static final UseCaseResponse<ReceiptModel> ERROR_NET_RESPONSE =
            new UseCaseResponse<>(new NetworkUseCaseError());

    public static final UseCaseResponse<ReceiptModel> ERROR_INTERNAL_RESPONSE =
            new UseCaseResponse<>(new InternalUseCaseError());

    public static final UseCaseResponse<ReceiptModel> ERROR_API_RESPONSE =
            new UseCaseResponse<>(new ApiUseCaseError());

}
