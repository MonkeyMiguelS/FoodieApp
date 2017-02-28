package com.jmperezra.foodie.views.modules.login;


import android.content.ComponentName;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.jmperezra.foodie.commons.EspressoDaggerMockRule;
import com.jmperezra.foodie.commons.EspressoTestMatchers;
import com.jmperezra.foodie.commons.network.HostSelectionInterceptor;
import com.jmperezra.foodie.views.modules.receipt.ReceiptActivity;
import com.mo2o.ruralvia.webserver.MockApiResponse;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.cosenonjaviste.daggermock.InjectFromComponent;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.SocketPolicy;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class LoginActivityFunTest {

    private final String aliasInput = "03030100";
    private final String passInput = "03030100";

    @Rule
    public EspressoDaggerMockRule rule = new EspressoDaggerMockRule();

    @Rule
    public IntentsTestRule<LoginActivity> mainActivityRule
            = new IntentsTestRule<>(LoginActivity.class, false, false);

    private LoginPageObject pageObject;

    private IdlingResource resourceOkhttp;

    private MockWebServer mockWebServer;

    @InjectFromComponent
    OkHttpClient okHttpClient;

    @InjectFromComponent
    HostSelectionInterceptor interceptor;

    @Before
    public void setUp() throws Exception {
        pageObject = new LoginPageObject();
        setupApiServer();
        setupInterceptorHttpClient();
        registerIdlings();
    }

    private void setupApiServer() throws Exception{
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockWebServer.url("/");
    }

    private void setupInterceptorHttpClient(){
        interceptor.setScheme(HostSelectionInterceptor.SchemeHttp.HTTP);
        interceptor.setPort(mockWebServer.getPort());
        interceptor.setHost(mockWebServer.getHostName());
    }

    private void registerIdlings(){
        resourceOkhttp = OkHttp3IdlingResource.create("OkHttp", okHttpClient);
        Espresso.registerIdlingResources(resourceOkhttp);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
        unregisterIdlings();
    }

    private void unregisterIdlings(){
        Espresso.unregisterIdlingResources(resourceOkhttp);
    }

    /**
     * Login es correcto cambiamos de actividad.
     *
     * @throws Exception
     */
    @Test
    public void shouldChangeActivityAfterLoginSuccess() throws Exception{
        mockWebServer.enqueue(MockApiResponse.serviceSE_RVA_LoginBE());
        mockWebServer.enqueue(MockApiResponse.serviceOKSE_CI_ConsultaDatosComerciosTpvs());

        startActivity();

        botWriteCredentials();

        pageObject.clickLogin();

        intended(hasComponent(new ComponentName(getTargetContext(), ReceiptActivity.class)));
        Assert.assertTrue(getActivity().isFinishing());
    }

    /**
     * Alias es incorrecto. Mostramos mensaje.
     *
     * @throws Exception
     */
    @Test
    public void shouldShowMessageErrorWhenIllegalAlias() throws Exception{
        mockWebServer.enqueue(MockApiResponse.serviceKoSE_RVA_LoginBEAliasError());

        startActivity();

        botWriteCredentials();

        pageObject.clickLogin();

        try {
            EspressoTestMatchers.checkIsNotDisplayed(pageObject.getSpinnerLoading());
        } catch (NoMatchingViewException e) {
            //view not displayed logic
        }
        EspressoTestMatchers.checkVisibility(pageObject.getErrorText(), ViewMatchers.Visibility.VISIBLE);
        EspressoTestMatchers.checkText(pageObject.getErrorText(), "Usuario no encontrado");
    }

    @Test
    public void shouldDisableLoginWhenIllegalAlias() throws Exception{
        String errorAlias = "";

        startActivity();

        botWriteAlias(errorAlias);

        EspressoTestMatchers.checkIsEnabled(pageObject.getLoginButton());
    }

    @Test
    public void shouldDisableLoginWhenIllegalPass() throws Exception{
        String errorPass = "";

        startActivity();

        botWritePass(errorPass);

        EspressoTestMatchers.checkIsEnabled(pageObject.getLoginButton());
    }

    @Test
    public void shouldEnableLoginWhenCredentialsOk() throws Exception{
        startActivity();

        botWriteCredentials();

        EspressoTestMatchers.checkIsEnabled(pageObject.getLoginButton());
    }

    /**
     * Sin conexión a internet. Mostramos notificación.
     *
     * @throws Exception
     */
    @Test
    public void shouldShowNetworkErrorWhenTimeOut() throws Exception{

        MockResponse mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setBody("{}");
        mockResponse.setSocketPolicy(SocketPolicy.NO_RESPONSE);

        mockWebServer.enqueue(mockResponse);

        startActivity();

        botWriteCredentials();

        pageObject.clickLogin();

        try {
            EspressoTestMatchers.checkIsNotDisplayed(pageObject.getSpinnerLoading());
        } catch (NoMatchingViewException e) {
            //view not displayed logic
        }
        EspressoTestMatchers.checkVisibility(pageObject.getErrorText(), ViewMatchers.Visibility.VISIBLE);
        EspressoTestMatchers.checkText(pageObject.getErrorText(), "Error de Red");
    }

    private void botWriteCredentials(){
        botWriteCredentials(aliasInput, passInput);
    }

    private void botWriteAlias(String alias){
        botWriteCredentials(alias, passInput);
    }

    private void botWritePass(String pass){
        botWriteCredentials(aliasInput, pass);
    }

    private void botWriteCredentials(String alias, String pass){
        pageObject.writeAliasInput(alias);
        pageObject.closeKeyboard(pageObject.getAliasInput());

        pageObject.writePasswordInput(pass);
        pageObject.closeKeyboard(pageObject.getPasswordInput());
    }

    private void startActivity(){
        mainActivityRule.launchActivity(new Intent());
    }

    private LoginActivity getActivity(){
        return mainActivityRule.getActivity();
    }
}