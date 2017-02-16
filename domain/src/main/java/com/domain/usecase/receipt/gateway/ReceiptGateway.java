package com.domain.usecase.receipt.gateway;

import com.domain.exceptions.ApiGatewayException;
import com.domain.exceptions.LocalGatewayException;
import com.domain.exceptions.NetworkGatewayException;
import com.domain.gateway.receipt.ReceiptApiGateway;
import com.domain.gateway.receipt.ReceiptLocalGateway;
import com.domain.models.ReceiptModel;

public class ReceiptGateway {

    private final ReceiptApiGateway apiGateway;
    private final ReceiptLocalGateway localGateway;

    public ReceiptGateway(ReceiptApiGateway apiGateway, ReceiptLocalGateway localGateway) {
        this.apiGateway = apiGateway;
        this.localGateway = localGateway;
    }

    public ReceiptModel getReceipt() throws LocalGatewayException, ApiGatewayException, NetworkGatewayException {
        try{
            return fromLocal();
        }catch (LocalGatewayException ex) {
            return fromApi();
        }
    }

    public ReceiptModel fromLocal() throws LocalGatewayException {
        ReceiptModel receiptModel = localGateway.obtainReceiptModel();
        if (receiptModel == null){
            throw new LocalGatewayException();
        }else{
            return receiptModel;
        }
    }

    public ReceiptModel fromApi() throws NetworkGatewayException, ApiGatewayException{
        ReceiptModel receiptModel = apiGateway.obtainReceiptModel();
        if (receiptModel == null){
            throw new ApiGatewayException();
        }else{
            return persistReceiptModel(receiptModel);
        }
    }

    private ReceiptModel persistReceiptModel(final ReceiptModel receiptModel){
        try{
            localGateway.persistReceiptModel(receiptModel);
        }finally {
            return receiptModel;
        }
    }
}
