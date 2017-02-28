package com.domain.usecase.receipt;

import com.domain.exceptions.ApiGatewayException;
import com.domain.exceptions.LocalGatewayException;
import com.domain.exceptions.NetworkException;
import com.domain.exceptions.errors.ApiUseCaseError;
import com.domain.exceptions.errors.InternalUseCaseError;
import com.domain.exceptions.errors.NetworkUseCaseError;
import com.domain.gateway.receipt.ReceiptApiGateway;
import com.domain.gateway.receipt.ReceiptLocalGateway;
import com.domain.models.ReceiptModel;
import com.domain.usecase.UseCase;
import com.domain.usecase.UseCaseError;
import com.domain.usecase.UseCaseResponse;

public class GetReceiptUseCase implements UseCase<UseCaseResponse<ReceiptModel>> {

    private final ReceiptApiGateway apiGateway;
    private final ReceiptLocalGateway localGateway;

    public GetReceiptUseCase(ReceiptLocalGateway localGateway, ReceiptApiGateway apiGateway) {
        this.localGateway = localGateway;
        this.apiGateway = apiGateway;
    }

    @Override
    public UseCaseResponse<ReceiptModel> call(){
        try{

            return fromLocal();
        }catch (LocalGatewayException ex) {
            return fromApi();
        }catch (Exception ex){
            return responseError(new InternalUseCaseError());
        }
    }

    private UseCaseResponse<ReceiptModel> fromLocal() throws LocalGatewayException {
        ReceiptModel receiptModel = localGateway.obtainReceiptModel();
        if (receiptModel == null){
            throw new LocalGatewayException();
        }else{
            return responseModel(receiptModel);
        }
    }

    private UseCaseResponse<ReceiptModel> fromApi(){
        try {
            ReceiptModel receiptModel = apiGateway.obtainReceiptModel();

            if (receiptModel == null){
                throw new ApiGatewayException();
            }else{
                return persistReceiptModel(receiptModel);
            }
        }catch (ApiGatewayException ex){
            return responseError(new ApiUseCaseError());
        }catch (NetworkException ex){
            return responseError(new NetworkUseCaseError());
        }
    }

    private UseCaseResponse<ReceiptModel> persistReceiptModel(final ReceiptModel receiptModel){
        try{
            localGateway.persistReceiptModel(receiptModel);
        }finally {
            return responseModel(receiptModel);
        }
    }

    private UseCaseResponse<ReceiptModel> responseModel(ReceiptModel receiptModel){
        return new UseCaseResponse<>(receiptModel);
    }

    private UseCaseResponse<ReceiptModel> responseError(UseCaseError error){
        return new UseCaseResponse<>(error);
    }
}
