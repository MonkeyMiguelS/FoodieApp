package com.presentation.modules.receipt;

import com.domain.exceptions.errors.ApiUseCaseError;
import com.domain.exceptions.errors.InternalUseCaseError;
import com.domain.exceptions.errors.NetworkUseCaseError;
import com.domain.models.ReceiptModel;
import com.domain.usecase.receipt.GetReceiptUseCase;
import com.presentation.AppViewInjector;
import com.presentation.Presenter;
import com.presentation.UseCaseExecution;
import com.presentation.UseCaseInvoker;
import com.presentation.UseCaseResult;

public class ReceiptPresenter extends Presenter<ReceiptView> {

    private GetReceiptUseCase getReceiptUseCase;
    private UseCaseInvoker useCaseInvoker;

    public ReceiptPresenter(AppViewInjector appViewInjector,
                            UseCaseInvoker useCaseInvoker,
                            GetReceiptUseCase getReceiptUseCase) {
        super(appViewInjector);
        this.useCaseInvoker = useCaseInvoker;
        this.getReceiptUseCase = getReceiptUseCase;
    }

    @Override
    public void onViewAttached() {
        obtainReceipt();
    }

    private void obtainReceipt(){
        try {
            getView().showSpinner();
            executeReceiptUseCase();
        }catch (Throwable ex){
            showInternalError();
        }
    }

    private void executeReceiptUseCase(){
        new UseCaseExecution<>(getReceiptUseCase).result(new UseCaseResult<ReceiptModel>() {
            @Override
            public void onResult(ReceiptModel model) {
                getView().hideSpinner();
                renderReceipt(model);
            }
        }).error(InternalUseCaseError.class, new UseCaseResult<InternalUseCaseError>() {
            @Override
            public void onResult(InternalUseCaseError error) {
                showInternalError();
            }
        }).error(ApiUseCaseError.class, new UseCaseResult<ApiUseCaseError>() {
            @Override
            public void onResult(ApiUseCaseError error) {
                showApiError();
            }
        }).error(NetworkUseCaseError.class, new UseCaseResult<NetworkUseCaseError>() {
            @Override
            public void onResult(NetworkUseCaseError error) {
                showNetworkError();
            }
        }).execute(useCaseInvoker);
    }

    private void renderReceipt(ReceiptModel receiptModel){
        getView().renderReceiptView(receiptModel);
    }

    private void showInternalError(){
        getView().hideSpinner();
        getView().showInternalError();
    }

    private void showApiError(){
        getView().hideSpinner();
        getView().showApiError();
    }

    private void showNetworkError(){
        getView().hideSpinner();
        getView().showNetworkError();
    }
}
