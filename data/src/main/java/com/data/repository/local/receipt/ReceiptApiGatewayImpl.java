package com.data.repository.local.receipt;

import com.domain.exceptions.ApiGatewayException;
import com.domain.exceptions.NetworkException;
import com.domain.gateway.receipt.ReceiptApiGateway;
import com.domain.models.ReceiptModel;

import javax.inject.Inject;

public class ReceiptApiGatewayImpl implements ReceiptApiGateway {


    @Inject
    public ReceiptApiGatewayImpl() {
    }

    @Override
    public ReceiptModel obtainReceiptModel() throws ApiGatewayException, NetworkException {
        return null;
    }
}
