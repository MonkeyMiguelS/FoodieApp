package com.domain.gateway.receipt;

import com.domain.exceptions.LocalGatewayException;
import com.domain.models.ReceiptModel;

public interface ReceiptLocalGateway {

    ReceiptModel obtainReceiptModel() throws LocalGatewayException;

    void persistReceiptModel(ReceiptModel receiptModel) throws LocalGatewayException;
}
