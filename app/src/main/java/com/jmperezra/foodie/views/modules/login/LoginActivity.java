package com.jmperezra.foodie.views.modules.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.jmperezra.foodie.FoodieApplication;
import com.jmperezra.foodie.R;
import com.jmperezra.foodie.di.modules.LoginModule;
import com.jmperezra.foodie.views.base.BaseActivity;
import com.jmperezra.foodie.views.customviews.buttons.OkButtonView;
import com.jmperezra.foodie.views.customviews.spinner.SpinnerLoading;
import com.jmperezra.foodie.views.modules.login.adapter.PageSlideAdapter;
import com.jmperezra.foodie.views.modules.login.factory.PageSlideLoginFactory;
import com.presentation.modules.login.LoginPresenter;
import com.presentation.modules.login.LoginView;
import com.viewpagerindicator.CirclePageIndicator;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {

    private final static int FLAG_ACTIVITY = Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK;

    @Inject
    LoginPresenter loginPresenter;

    @Inject
    SpinnerLoading spinnerLoading;

    @Bind(R.id.vpLogin)
    ViewPager viewPagerImageLogin;

    @Bind(R.id.viewIndicator)
    CirclePageIndicator pagerIndicator;

    @Bind(R.id.inputAlias)
    TextInputEditText inputAlias;

    @Bind(R.id.inputPassword)
    TextInputEditText inputPassword;

    @Bind(R.id.viewTextError)
    TextView viewTextError;

    @Bind(R.id.buttonLogin)
    OkButtonView okButtonView;

    public static Intent getCallingIntent(@NonNull Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(FLAG_ACTIVITY);
        return intent;
    }

    @Override
    protected void setupActivityComponent() {
        getComponentsHelper()
                .getAppComponent()
                .plus(new LoginModule(this)).inject(this);
    }

    @Override
    @LayoutRes
    protected int getActivityLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        loginPresenter.attachView(this);
    }

    @Override
    public void releaseLocalStorage(){
        ((FoodieApplication)getApplicationContext()).getDataBaseHelper().initalize();
    }

    public void setupView(){
        setupSlidePager();
        setupPagerIndicator();
        setupInputAlias();
        setupInputPassword();
    }

    private void setupSlidePager(){
        viewPagerImageLogin.setAdapter(new PageSlideAdapter(PageSlideLoginFactory.makePages()));
    }

    private void setupPagerIndicator(){
        pagerIndicator.setViewPager(viewPagerImageLogin);
    }

    private void setupInputAlias(){
        inputAlias.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginPresenter.validateAliasInput(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void setupInputPassword(){
        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginPresenter.validatePassInputs(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        inputPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //// TODO: 16/2/17 que intervenga el presentador.
                    if (okButtonView.isEnabled()){
                        hideKeyboard();
                        onClickDoLogin();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @OnClick(R.id.buttonLogin)
    public void onClickDoLogin(){
        loginPresenter.loginClicked(getInputAlias(), getInputPassword());
    }

    public String getInputAlias(){
        return inputAlias.getText().toString();
    }

    public String getInputPassword(){
        return inputPassword.getText().toString();
    }

    @Override
    public void loginSuccess() {
        navigator.navigateToReceiptView(this);
        finish();
    }

    @Override
    public void showLoginError(String message) {
        viewTextError.setText(message);
        viewTextError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginError(){
        viewTextError.setText("");
        viewTextError.setVisibility(View.GONE);
    }

    @Override
    public void disableButtonLogin(){
        okButtonView.disableButton();
    }

    @Override
    public void enableButtonLogin(){
        okButtonView.enableButton();
    }

    @Override
    public void showSpinner() {
        spinnerLoading.show();
    }

    @Override
    public void hideSpinner() {
        spinnerLoading.dismiss();
    }

    @Override
    public void showNetworkError() {
        viewTextError.setText("Error de Red");
        viewTextError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showInternalError() {
        viewTextError.setText("Error inesperado");
        viewTextError.setVisibility(View.VISIBLE);
    }
}

