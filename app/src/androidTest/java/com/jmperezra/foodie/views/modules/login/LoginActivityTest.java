package com.jmperezra.foodie.views.modules.login;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.ActionBar;
import android.text.InputType;

import com.jmperezra.foodie.R;
import com.jmperezra.foodie.commons.EspressoDaggerMockRule;
import com.jmperezra.foodie.commons.EspressoTestMatchers;
import com.jmperezra.foodie.views.customviews.spinner.SpinnerLoading;
import com.jmperezra.foodie.views.navigation.Navigator;
import com.presentation.modules.login.LoginPresenter;
import com.presentation.modules.login.LoginView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.not;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class LoginActivityTest {

    private final static int FLAG_NEW_TASK_ACTIVITY
            = Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK;

    @Rule
    public EspressoDaggerMockRule rule = new EspressoDaggerMockRule();

    @Rule
    public IntentsTestRule<LoginActivity> mainActivityRule
            = new IntentsTestRule<>(LoginActivity.class, false, false);

    @Mock
    LoginPresenter presenter;

    @Mock
    Navigator navigator;

    @Mock
    SpinnerLoading spinnerLoading;

    private Resources resources;
    private LoginPageObject pageObject;

    @Before
    public void setUp() throws Exception {
        pageObject = new LoginPageObject();
        resources = InstrumentationRegistry.getTargetContext().getResources();
    }


    @Test
    public void shouldReturnIntentWithFlagNewTask() throws Exception{
        startActivity();
        Intent intent = LoginActivity.getCallingIntent(getActivity());
        Assert.assertTrue(intent.getFlags() == FLAG_NEW_TASK_ACTIVITY);
        Assert.assertTrue(intent.getComponent()
                .getShortClassName()
                .equals(getActivity().getComponentName().getShortClassName()));
    }


    /**
     * La vista tiene el ID X del layout.
     *
     * @throws Exception
     */
    @Test
    public void shouldBindLayout() throws Exception{
        startActivity();
        Assert.assertTrue(mainActivityRule.getActivity().getActivityLayout() == R.layout.activity_login);
    }

    /**
     * Pantalla sin ActionBar
     *
     * @throws Exception
     */
    @Test
    public void shouldToBeFullScreen() throws Exception{
        try {
            startActivity();
            ActionBar actionBar = mainActivityRule.getActivity().getSupportActionBar();
            Assert.assertNull(actionBar);
        }catch (NoMatchingViewException e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void shouldPassInputToBeTypePassword() throws Exception{
        startActivity();
        int value = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        Assert.assertTrue(getActivity().inputPassword.getInputType() == value);
    }

    /**
     * Al iniciar la actividad, se ejecuta el método attachView.
     *
     * @throws Exception
     */
    @Test
    public void shouldCallAttachViewWhenStartActivity() throws Exception{
        Mockito
            .doNothing()
            .when(presenter).attachView(Matchers.<LoginView>any());

        startActivity();

        verify(presenter).attachView(any(LoginView.class));
    }

    /**
     * Al iniciar la actividad, el input del alias es visible.
     *
     * @throws Exception
     */
    @Test
    public void shouldAliasInputToBeVisibleWhenStartActivity() throws Exception{
        startActivity();
        EspressoTestMatchers.checkIsVisible(pageObject.getAliasInput());
    }

    /**
     * al iniciar la actividad, el input del password es visible.
     *
     * @throws Exception
     */
    @Test
    public void shouldPasswordInputToBeVisibleWhenStartActivity() throws Exception{
        startActivity();
        EspressoTestMatchers.checkIsVisible(pageObject.getPasswordInput());
    }

    /**
     * Al iniciar la actividad, el botón del login es visible.
     *
     * @throws Exception
     */
    @Test
    public void shouldLoginButtonToBeVisibleWhenStartActivity() throws Exception{
        startActivity();
        EspressoTestMatchers.checkIsVisible(pageObject.getPasswordInput());
    }

    /**
     * Al iniciar la actividad, el hint del Alias es una cadena X.
     *
     * @throws Exception
     */
    @Test
    public void shouldSetHintAliasInputWhenStartActivity() throws Exception{
        startActivity();
        EspressoTestMatchers.checkHint(pageObject.getAliasInputLayout(), getTextResource(R.string.text_hint_alias));
    }

    /**
     * Al iniciar la actividad, el hint del Password es una cadena X
     *
     * @throws Exception
     */
    @Test
    public void shouldSetHintPasswordInputWhenStartActivity() throws Exception{
        startActivity();
        EspressoTestMatchers.checkHint(pageObject.getPasswordInputLayout(), getTextResource(R.string.text_hint_password));
    }

    /**
     * Cuando se inicia la vista, el campo password está vacío.
     *
     * @throws Exception
     */
    @Test
    public void shouldToBeEmptyPasswordInputWhenStartActivity() throws Exception{
        startActivity();
        getActivity().inputPassword.getText().toString();
        EspressoTestMatchers.checkText(pageObject.getPasswordInput(), "");
    }

    /**
     * Cuando se inicia la vista, el campo alias está vacío.
     * @throws Exception
     */
    @Test
    public void shouldToBeEmptyAliasInputWhenStartActivity() throws Exception{
        startActivity();
        EspressoTestMatchers.checkText(pageObject.getAliasInput(), "");
    }

    /**
     * Al iniciar la actividad, el texto del botón es X.
     *
     * @throws Exception
     */
    @Test
    public void shouldSetTextLoginButtonWhenStartActivity() throws Exception{
        startActivity();
        EspressoTestMatchers.checkText(pageObject.getLoginButton(), getTextResource(R.string.label_button_login));
    }

    /**
     * Cuando se inicia la actividad, se configura el view-pager con un adaptador.
     *
     * @throws Exception
     */
    @Test
    public void shouldSlidePagesHasAdapterWhenStartActivity() throws Exception{
        startActivity();
        Assert.assertNotNull(getActivity().viewPagerImageLogin.getAdapter());
    }

    /**
     * Cuando se muestra la vista de login, se muestra un page-indicator.
     *
     * @throws Exception
     */
    @Test
    public void shouldPagerIndicatorDisplayedWhenStartActivity() throws Exception{
        startActivity();
        EspressoTestMatchers.checkIsVisible(pageObject.getPagerIndicator());
    }

    /**
     * La pantalla Login tiene un viewpager
     *
     * @throws Exception
     */
    @Test
    public void shouldSlidePagesDisplayedWhenStartActivity() throws Exception{
        startActivity();
        EspressoTestMatchers.checkIsVisible(pageObject.getViewPager());
    }

    /**
     * Cuando se inicia la vista, el ViewPager tiene 4 Slides.
     *
     * @throws Exception
     */
    @Test
    public void shouldSlidePageHaveFourPages() throws Exception{
        startActivity();
        Assert.assertTrue(mainActivityRule.getActivity().viewPagerImageLogin.getAdapter().getCount() == 4);
    }

    /**
     * Cuando se hace click en el botón de login, se hace Login (presentador)
     *
     * @throws Exception
     */
    @Test
    public void shouldCallDoLoginWhenLoginButtonClicked() throws Exception{
        startActivity();
        pageObject.clickLogin();
        verify(presenter).loginClicked(any(String.class), any(String.class));
    }

    /**
     * Cuando se hace click en el botón del login, se hace login con el string de alias y password
     *
     * @throws Exception
     */
    @Test
    public void shouldSetAliasInput() throws Exception{
        String alias = "alias1234";
        startActivity();

        pageObject.writeAliasInput(alias);
        pageObject.closeKeyboard(pageObject.getAliasInput());

        EspressoTestMatchers.checkText(pageObject.getAliasInput(), alias);
    }

    @Test
    public void shouldSetPassInput() throws Exception{
        String pass = "pass1234";

        startActivity();

        pageObject.writePasswordInput(pass);
        pageObject.closeKeyboard(pageObject.getPasswordInput());

        EspressoTestMatchers.checkText(pageObject.getPasswordInput(), pass);
    }

    @Test
    public void shouldEnableLoginButton() throws Exception{
        startActivity();
        getActivity().enableButtonLogin();
        EspressoTestMatchers.checkIsEnabled(pageObject.getLoginButton());
    }

    @Test
    public void shouldDisableLoginButton() throws Throwable{
        startActivity();
        mainActivityRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getActivity().disableButtonLogin();
            }
        });
        EspressoTestMatchers.checkIsDisabled(pageObject.getLoginButton());
    }

    /**
     * Si se ejecuta el método de login correcto, se cambia de actividad.
     *
     * @throws Exception
     */
    @Test
    public void shouldChangeActivityWhenLoginSuccess() throws Exception{
        startActivity();
        getActivity().loginSuccess();

        verify(navigator).navigateToReceiptView(any(Context.class));
    }

    /**
     * Se muestra un spinner.
     *
     * @throws Exception
     */
    @Test
    public void shouldShowSpinner() throws Exception{
        startActivity();
        getActivity().showSpinner();

        verify(spinnerLoading).show();
    }

    /**
     * Se oculta un spinner.
     *
     * @throws Exception
     */
    @Test
    public void shouldHideSpinner() throws Exception{
        startActivity();
        getActivity().hideSpinner();

        verify(spinnerLoading).dismiss();
    }

    /**
     * Cuando se llama mostrar error, se muestra la notificación.
     *
     * @throws Exception
     */
    @Test
    public void shouldShowErrorMessage() throws Throwable{
        final String error = "Esto es un error";
        startActivity();
        mainActivityRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getActivity().showLoginError(error);
            }
        });
        EspressoTestMatchers.checkText(pageObject.getErrorText(), error);
        EspressoTestMatchers.checkIsVisible(pageObject.getErrorText());
    }

    /**
     * Cuando se inicia la vista, no hay ningun error.
     *
     * @throws Exception
     */
    @Test
    public void shouldErrorMessageToBeHiddenWhenStartActivity() throws Exception{
        startActivity();
        pageObject.getErrorText().check(matches(not(isDisplayed())));
    }

    /**
     * Cuando se oculta el error del login, se oculta la notificación
     * @throws Exception
     */
    @Test
    public void shouldErrorMessageToBeHidden() throws Exception{
        startActivity();
        getActivity().hideLoginError();
        EspressoTestMatchers.checkVisibility(pageObject.getErrorText(), ViewMatchers.Visibility.GONE);
    }

    /**
     * Cuando se oculta un error, la cadena de texto del error es vacía "";
     * @throws Exception
     */
    @Test
    public void shouldErrorMessageToBeEmpty() throws Exception{
        startActivity();
        getActivity().hideLoginError();
        EspressoTestMatchers.checkText(pageObject.getErrorText(), "");
    }

    /**
     * Ocultar el teclado.
     * @throws Exception
     */
    @Test
    @Ignore
    public void shouldHideKeyboard() throws Exception{
        startActivity();
        pageObject.writeAliasInput("alias");
        getActivity().hideKeyboard();
    }

    @Test
    public void shouldReturnAlias() throws Exception{
        String inputAlias = "alias";

        startActivity();
        pageObject.writeAliasInput(inputAlias);
        pageObject.closeKeyboard(pageObject.getAliasInput());

        EspressoTestMatchers.checkText(pageObject.getAliasInput(), inputAlias);
    }

    @Test
    public void shouldReturnPassword() throws Exception{
        String inputPassword = "password";

        startActivity();
        pageObject.writePasswordInput(inputPassword);
        pageObject.closeKeyboard(pageObject.getPasswordInput());

        EspressoTestMatchers.checkText(pageObject.getPasswordInput(), inputPassword);
    }

    private String getTextResource(int stringResId){
        return resources.getString(stringResId);
    }

    private void startActivity(){
        mainActivityRule.launchActivity(new Intent());
    }

    private LoginActivity getActivity(){
        return mainActivityRule.getActivity();
    }
}