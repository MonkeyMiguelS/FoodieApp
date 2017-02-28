package com.jmperezra.foodie.views.modules.login.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.jmperezra.foodie.views.modules.login.pageslide.PageSlide;
import com.jmperezra.foodie.views.modules.login.pageslide.PageSlideBuilder;

import java.util.List;


public class PageSlideAdapter extends PagerAdapter {

    private final List<PageSlide> pagesSlide;

    public PageSlideAdapter(List<PageSlide> pagesSlide) {
        this.pagesSlide = pagesSlide;
    }

    @Override
    public int getCount() {
        return (pagesSlide != null) ? pagesSlide.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public View instantiateItem(ViewGroup collection, int position) {
        View viewPageSlide = PageSlideBuilder
                .builder(collection)
                .build(pagesSlide.get(position));

        collection.addView(viewPageSlide);

        return viewPageSlide;
    }

}
