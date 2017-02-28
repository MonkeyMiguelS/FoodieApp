package com.presentation.modules.receipt;

import com.domain.models.ReceiptModel;
import com.domain.usecase.UseCaseResponse;
import com.domain.usecase.receipt.GetReceiptUseCase;
import com.presentation.testcommons.FakeUseCaseInvoker;
import com.presentation.testcommons.FakeViewInjector;
import com.presentation.testcommons.ReceiptStub;
import com.presentation.testcommons.ResponseStub;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptPresenterTest {

    ReceiptPresenter receiptPresenter;

    @Mock
    GetReceiptUseCase getReceiptUseCase;

    @Mock
    ReceiptView receiptView;

    ReceiptModel receiptModel;

    public UseCaseResponse<ReceiptModel> successResponse;

    @Before
    public void setUp() throws Exception {
        receiptPresenter = new ReceiptPresenter(
            new FakeViewInjector(),
            FakeUseCaseInvoker.create(),
            getReceiptUseCase
        );
        receiptModel = ReceiptStub.getReceipt();
        successResponse = new UseCaseResponse<>(receiptModel);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfDontViewAttache() throws Exception{
        receiptPresenter.onViewAttached();
    }

    @Test
    public void shouldShowSpinnerWhenInitPresenter() throws Exception{
        receiptPresenter.attachView(receiptView);
        Mockito.verify(receiptView, times(1)).showSpinner();
    }

    @Test
    public void shouldRenderReceiptViewWhenInitPresenter() throws Exception{
        when(getReceiptUseCase.call()).thenReturn(successResponse);
        receiptPresenter.attachView(receiptView);

        Mockito.verify(receiptView, times(1)).showSpinner();
        Mockito.verify(receiptView, times(1)).hideSpinner();
        Mockito.verify(receiptView, times(1)).renderReceiptView(receiptModel);
        //-->Mockito.verify(receiptView).renderReceiptView(any(ReceiptModel.class));
    }

    @Test
    public void shouldShowNetworkErrorWhenInternetErrorConnection() throws Exception{
        when(getReceiptUseCase.call()).thenReturn(ResponseStub.ERROR_NET_RESPONSE);

        receiptPresenter.attachView(receiptView);

        Mockito.verify(receiptView, times(1)).showSpinner();
        Mockito.verify(receiptView, times(1)).hideSpinner();
        Mockito.verify(receiptView, times(1)).showNetworkError();
    }

    @Test
    public void shouldShowInternalErrorWhenInternalErrorResponse() throws Exception{
        when(getReceiptUseCase.call()).thenReturn(ResponseStub.ERROR_INTERNAL_RESPONSE);

        receiptPresenter.attachView(receiptView);

        Mockito.verify(receiptView, times(1)).showSpinner();
        Mockito.verify(receiptView, times(1)).hideSpinner();
        Mockito.verify(receiptView, times(1)).showInternalError();
    }

    @Test
    public void shouldShowApiErrorWhenApiErrorResponse() throws Exception{
        when(getReceiptUseCase.call()).thenReturn(ResponseStub.ERROR_API_RESPONSE);

        receiptPresenter.attachView(receiptView);

        InOrder order = inOrder(receiptView);
        order.verify(receiptView).showSpinner();
        order.verify(receiptView).hideSpinner();
        order.verify(receiptView).showApiError();
    }
}