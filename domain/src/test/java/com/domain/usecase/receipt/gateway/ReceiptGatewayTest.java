package com.domain.usecase.receipt.gateway;

import com.domain.testcommons.stubs.ReceiptStub;
import com.domain.gateway.receipt.ReceiptApiGateway;
import com.domain.gateway.receipt.ReceiptLocalGateway;
import com.domain.models.ReceiptModel;
import com.domain.usecase.receipt.GetReceiptUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptGatewayTest {

    GetReceiptUseCase getReceiptUseCase;

    @Mock
    ReceiptLocalGateway localGateway;

    @Mock
    ReceiptApiGateway apiGateway;

    ReceiptModel receiptModel;

    @Before
    public void setUp() throws Exception {
        getReceiptUseCase = new GetReceiptUseCase(localGateway, apiGateway);
        receiptModel = ReceiptStub.getReceipt();
        receiptModel = ReceiptStub.getReceipt();
    }

    @Test
    public void demo(){

    }
}