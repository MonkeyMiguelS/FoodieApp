package com.domain.usecase.receipt;

import com.domain.testcommons.stubs.ReceiptStub;
import com.domain.exceptions.ApiGatewayException;
import com.domain.exceptions.LocalGatewayException;
import com.domain.exceptions.NetworkException;
import com.domain.exceptions.errors.ApiUseCaseError;
import com.domain.exceptions.errors.NetworkUseCaseError;
import com.domain.gateway.receipt.ReceiptApiGateway;
import com.domain.gateway.receipt.ReceiptLocalGateway;
import com.domain.models.ReceiptModel;
import com.domain.usecase.UseCaseResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetReceiptUseCaseTest {

    GetReceiptUseCase getReceiptUseCase;

    @Mock
    ReceiptLocalGateway localGateway;

    @Mock
    ReceiptApiGateway apiGateway;

    UseCaseResponse<ReceiptModel> successResponse;

    ReceiptModel receiptModel;

    @Before
    public void setUp() throws Exception {
        getReceiptUseCase = new GetReceiptUseCase(localGateway, apiGateway);
        receiptModel = ReceiptStub.getReceipt();
        successResponse = new UseCaseResponse<>(receiptModel);
    }

    /**
     * Si existe el model en local, entonces lo respuesta con el modelo no es null.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnModel() throws Exception{
        when(localGateway.obtainReceiptModel()).thenReturn(receiptModel);

        UseCaseResponse<ReceiptModel> response = getReceiptUseCase.call();

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getResult().equals(receiptModel));
    }

    /**
     * Si no existe el modelo en local, entonces llamamos a la API para obtenerlo.
     *
     * @throws Exception
     */
    @Test
    public void shouldObtainReceiptModel() throws Exception{
        when(localGateway.obtainReceiptModel()).thenReturn(null);
        when(apiGateway.obtainReceiptModel()).thenReturn(receiptModel);

        getReceiptUseCase.call();

        //Se repite con el de abajo
        Mockito.verify(apiGateway).obtainReceiptModel();
    }

    /**
     * Si no existe el modelo en local, entonces obtenemos el modelo de la API.
     *
     * @throws Exception
     */
    @Test
    public void shouldObtainReceiptModelFromApi() throws Exception{
        when(localGateway.obtainReceiptModel()).thenReturn(null);
        when(apiGateway.obtainReceiptModel()).thenReturn(receiptModel);

        UseCaseResponse<ReceiptModel> response = getReceiptUseCase.call();

        Mockito.verify(apiGateway).obtainReceiptModel();
        Assert.assertTrue(response.getResult().equals(receiptModel));
    }

    /**
     * Si la API devuelve una excepción, entonces devolvemos un error de tipo ApiError.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnApiUseCaseErrorWhenServiceThrowException() throws Exception{
        doThrow(LocalGatewayException.class).when(localGateway).obtainReceiptModel();
        doThrow(ApiGatewayException.class).when(apiGateway).obtainReceiptModel();

        UseCaseResponse<ReceiptModel> response = getReceiptUseCase.call();

        Assert.assertTrue(response.hasError());
        Assert.assertTrue(response.getError().getClass().equals(ApiUseCaseError.class));
    }

    /**
     * Si no tenemos conexión, entonces se devuelve un NetworkError
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnNetworkErrorWhenThereIsNotInternet() throws Exception{
        when(localGateway.obtainReceiptModel()).thenReturn(null);

        doThrow(ApiGatewayException.class).when(apiGateway).obtainReceiptModel();

        UseCaseResponse<ReceiptModel> response = getReceiptUseCase.call();

        Assert.assertTrue(response.hasError());
        Assert.assertTrue(response.getError().getClass().equals(ApiUseCaseError.class));
    }

    /**
     * Si obtenemos el modelo de la API, entonces llamamos al método para persistirlo.
     *
     * @throws Exception
     */
    @Test
    public void shouldPersistApiWhenObtainReceiptFromApi() throws Exception{
        when(localGateway.obtainReceiptModel()).thenReturn(null);

        when(apiGateway.obtainReceiptModel()).thenReturn(receiptModel);

        doNothing().when(localGateway).persistReceiptModel(receiptModel);

        getReceiptUseCase.call();

        Mockito.verify(localGateway).persistReceiptModel(receiptModel);
    }

    /**
     * Si ocurre un error al persistir el modelo, entonces devolvemos un error de mensaje.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnReceiptModelWhenPersistReceipt() throws Exception{
        when(localGateway.obtainReceiptModel()).thenReturn(null);

        when(apiGateway.obtainReceiptModel()).thenReturn(receiptModel);

        doThrow(LocalGatewayException.class).when(localGateway).persistReceiptModel(receiptModel);

        UseCaseResponse<ReceiptModel> response = getReceiptUseCase.call();

        Assert.assertTrue(!response.hasError());
        Assert.assertTrue(response.getResult().equals(receiptModel));
    }

    @Test
    public void shouldObtainReceiptFromApiWhenThereIsErrorInLocal() throws Exception{
        doThrow(LocalGatewayException.class).when(localGateway).obtainReceiptModel();

        getReceiptUseCase.call();

        Mockito.verify(apiGateway).obtainReceiptModel();
    }

    @Test
    public void shouldReturnNetworkErrorWhenThrowNetworkException() throws Exception{
        when(localGateway.obtainReceiptModel()).thenReturn(null);

        doThrow(NetworkException.class).when(apiGateway).obtainReceiptModel();

        UseCaseResponse<ReceiptModel> response = getReceiptUseCase.call();

        Assert.assertTrue(response.hasError());
        Assert.assertTrue(response.getError().getClass().equals(NetworkUseCaseError.class));
    }
}