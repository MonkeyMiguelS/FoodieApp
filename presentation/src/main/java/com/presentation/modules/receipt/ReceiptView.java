package com.presentation.modules.receipt;

import com.domain.models.ReceiptModel;

import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView;

@ThreadDecoratedView
public interface ReceiptView {

    void showSpinner();

    void hideSpinner();

    void renderReceiptView(ReceiptModel receiptModel);

    void showNetworkError();

    void showApiError();

    void showInternalError();
}
