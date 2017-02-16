package com.domain.gateway.receipt;

import com.domain.exceptions.ApiGatewayException;
import com.domain.exceptions.NetworkGatewayException;
import com.domain.models.ReceiptModel;

public interface ReceiptApiGateway {

    ReceiptModel obtainReceiptModel() throws ApiGatewayException, NetworkGatewayException;

}
