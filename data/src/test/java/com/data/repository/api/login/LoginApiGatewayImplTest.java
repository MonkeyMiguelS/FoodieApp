package com.data.repository.api.login;

import com.data.repository.api.ApiEndPoints;
import com.data.repository.api.login.models.ApiLoginModel;
import com.data.repository.api.login.models.mappers.ApiAuthMapper;
import com.data.testcommons.webserver.MockApiResponse;
import com.data.testcommons.webserver.TestNetworkModule;
import com.domain.commons.logger.Logger;
import com.domain.exceptions.ApiGatewayException;
import com.domain.exceptions.NetworkException;
import com.domain.models.AuthModel;
import com.domain.models.SessionModel;
import com.jmperezra.data.BuildConfig;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.net.UnknownHostException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.SocketPolicy;

import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class LoginApiGatewayImplTest {

    private TestNetworkModule networkModule;
    private MockWebServer mockWebServer;
    private LoginApiGatewayImpl apiGateway;

    public LoginApiGatewayImplTest() {
        networkModule = new TestNetworkModule();
    }

    Logger logger = Mockito.mock(Logger.class);

    @Before
    public void setUp() throws Exception {
        mockWebServer = networkModule.getMockWebServer();
        apiGateway = new LoginApiGatewayImpl(
                logger,
                networkModule.getApiService(),
                new ApiAuthMapper());
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void shouldHasSession() throws Exception {
        mockWebServer.enqueue(MockApiResponse.serviceLoginSuccess());

        AuthModel authModel = new AuthModel("user_valid","pass_valid");

        SessionModel sessionModel = apiGateway.doAuth(authModel);

        Assert.assertTrue(sessionModel != null);
    }

    @Test
    public void shouldHasToken() throws Exception {
        mockWebServer.enqueue(MockApiResponse.serviceLoginSuccess());

        AuthModel authModel = new AuthModel("user_valid","pass_valid");

        SessionModel sessionModel = apiGateway.doAuth(authModel);
        Assert.assertNotNull(sessionModel.getAuthModel().getToken());
        Assert.assertTrue(sessionModel.getAuthModel().getToken().equals(MockApiResponse.MOCK_DEFAULT_TOKEN));
    }

    @Test(expected = ApiGatewayException.class)
    public void shouldThrowApiGatewayExceptionWhenIllegalPassword() throws Exception {
        mockWebServer.enqueue(MockApiResponse.serviceLoginPassError());

        AuthModel authModel = new AuthModel("user_valid","pass_dont_valid");

        apiGateway.doAuth(authModel);
    }


    @Test(expected = ApiGatewayException.class)
    public void shouldThrowApiGatewayExceptionWhenIllegalAlias() throws Exception {
        mockWebServer.enqueue(MockApiResponse.serviceLoginAliasError());

        AuthModel authModel = new AuthModel("user_dont_valid","pass_valid");

        apiGateway.doAuth(authModel);
    }


    @Test(expected = ApiGatewayException.class)
    public void shouldThrowApiGatewayExceptionWhenIllegalCredentials() throws Exception {
        mockWebServer.enqueue(MockApiResponse.serviceLoginAliasError());

        AuthModel authModel = new AuthModel("user_dont_valid","pass_dont_valid");

        apiGateway.doAuth(authModel);
    }

    @Test(expected = NetworkException.class)
    public void shouldNetworkExceptionWhenTimeOut() throws Exception {
        mockWebServer.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.NO_RESPONSE));
        apiGateway.doAuth(new AuthModel("user","pass"));
    }

    @Test(expected = ApiGatewayException.class)
    public void shouldApiGatewayExceptionWhenInternalServerError() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500));
        apiGateway.doAuth(new AuthModel("user","pass"));
    }

    @Test(expected = NetworkException.class)
    public void shouldNetworkExceptionWhenWithoutInternetConnection() throws Exception {
        ApiEndPoints apiEndPoints = Mockito.mock(ApiEndPoints.class);

        when(apiEndPoints.serviceDoAuth(Mockito.any(ApiLoginModel.class)))
            .thenThrow(UnknownHostException.class);

        LoginApiGatewayImpl apiGateway
                = new LoginApiGatewayImpl(logger, apiEndPoints, new ApiAuthMapper());

        apiGateway.doAuth(new AuthModel("user", "pass"));
    }
}