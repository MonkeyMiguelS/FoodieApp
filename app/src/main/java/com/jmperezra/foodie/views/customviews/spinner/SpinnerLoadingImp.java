package com.jmperezra.foodie.views.customviews.spinner;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;

import com.jmperezra.foodie.R;

public class SpinnerLoadingImp implements SpinnerLoading{

    private Context context;
    private ProgressDialog progressDialog;
    private ProgressBar progressBar;

    public SpinnerLoadingImp(Context context) {
        this.context = context;
        setupSpinnerView();
    }

    private void setupSpinnerView(){
        progressDialog = new ProgressDialog(this.context);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        progressBar = new ProgressBar(context);
        progressBar.setId(R.id.spinner_progress_bar);
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(context, R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        progressBar.setBackgroundResource(android.R.color.transparent);
    }

    @Override
    public void show() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
            progressDialog.setContentView(progressBar);
        }
    }

    @Override
    public void show(@NonNull final SpinnerLoadingListener listener){
        progressDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                listener.onFinishAction();
            }
        });
        this.show();
    }

    @Override
    public void dismiss(){
        if(progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void dismiss(@NonNull final SpinnerLoadingListener listener) {
        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                listener.onFinishAction();
            }
        });
        this.dismiss();
    }
}
