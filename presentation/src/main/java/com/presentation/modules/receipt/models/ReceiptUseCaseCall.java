package com.presentation.modules.receipt.models;

import com.domain.exceptions.errors.ApiUseCaseError;
import com.domain.exceptions.errors.InternalUseCaseError;
import com.domain.exceptions.errors.NetworkUseCaseError;
import com.domain.models.ReceiptModel;
import com.domain.usecase.receipt.GetReceiptUseCase;
import com.presentation.UseCaseExecution;
import com.presentation.UseCaseInvoker;
import com.presentation.UseCaseResult;

public class ReceiptUseCaseCall {

    private GetReceiptUseCase getReceiptUseCase;
    private UseCaseInvoker useCaseInvoker;

    public ReceiptUseCaseCall(UseCaseInvoker useCaseInvoker,
                            GetReceiptUseCase getReceiptUseCase) {
        this.useCaseInvoker = useCaseInvoker;
        this.getReceiptUseCase = getReceiptUseCase;
    }


    public void executeUseCase(){
        new UseCaseExecution<>(getReceiptUseCase).result(new UseCaseResult<ReceiptModel>() {
            @Override
            public void onResult(ReceiptModel model) {
                //getView().hideSpinner();
                //renderReceipt(model);
            }
        }).error(InternalUseCaseError.class, new UseCaseResult<InternalUseCaseError>() {
            @Override
            public void onResult(InternalUseCaseError error) {
                //showInternalError();
            }
        }).error(ApiUseCaseError.class, new UseCaseResult<ApiUseCaseError>() {
            @Override
            public void onResult(ApiUseCaseError error) {
                //showApiError();
            }
        }).error(NetworkUseCaseError.class, new UseCaseResult<NetworkUseCaseError>() {
            @Override
            public void onResult(NetworkUseCaseError error) {
                //showNetworkError();
            }
        }).execute(useCaseInvoker);
    }


}
