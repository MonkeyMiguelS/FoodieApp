package com.data.repository.api.receipt;

import com.domain.exceptions.LocalGatewayException;
import com.domain.gateway.receipt.ReceiptLocalGateway;
import com.domain.models.ReceiptModel;

import javax.inject.Inject;

public class ReceiptLocalGatewayImpl implements ReceiptLocalGateway {


    @Inject
    public ReceiptLocalGatewayImpl() {

    }

    @Override
    public ReceiptModel obtainReceiptModel() throws LocalGatewayException {
        return null;
    }

    @Override
    public void persistReceiptModel(ReceiptModel receiptModel) throws LocalGatewayException {

    }
}
