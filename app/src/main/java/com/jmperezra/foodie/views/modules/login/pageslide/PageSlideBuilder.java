package com.jmperezra.foodie.views.modules.login.pageslide;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmperezra.foodie.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PageSlideBuilder {

    private ViewGroup containerView = null;

    @Bind(R.id.bkgImageSlide)
    ImageView imageSlide;

    @Bind(R.id.titlePage)
    TextView titlePage;

    private PageSlideBuilder(ViewGroup containerView) {
        this.containerView = containerView;
    }

    public static PageSlideBuilder builder(@NonNull ViewGroup containerView){
        return new PageSlideBuilder(containerView);
    }

    private Context getContext(){
        return containerView.getContext();
    }

    public View build(@NonNull PageSlide pageSlide){
        View view = getInflateView();
        setTitlePage(pageSlide);
        setImagePage(pageSlide);
        return view;
    }

    private View getInflateView(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View viewInflated =  inflater.inflate(R.layout.view_page_slide_login, containerView, false);
        ButterKnife.bind(this, viewInflated);
        return viewInflated;
    }

    private void setTitlePage(PageSlide pageSlide){
        titlePage.setText(getContext().getText(pageSlide.getStringResId()));
    }

    private void setImagePage(PageSlide pageSlide){
        imageSlide.setImageDrawable(getContext().getResources().getDrawable(pageSlide.getImageResId()));
    }
}
