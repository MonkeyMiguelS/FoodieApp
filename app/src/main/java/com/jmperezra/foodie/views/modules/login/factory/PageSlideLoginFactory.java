package com.jmperezra.foodie.views.modules.login.factory;


import com.jmperezra.foodie.views.modules.login.pageslide.ReceiptPageSlide;
import com.jmperezra.foodie.views.modules.login.pageslide.EvolutionPageSlide;
import com.jmperezra.foodie.views.modules.login.pageslide.PageSlide;
import com.jmperezra.foodie.views.modules.login.pageslide.IngredientsPageSlide;
import com.jmperezra.foodie.views.modules.login.pageslide.HealthyFoodPageSlide;

import java.util.LinkedList;
import java.util.List;

public class PageSlideLoginFactory {

    public static List<PageSlide> makePages(){
        List<PageSlide> pageSlides = new LinkedList<>();
        pageSlides.add(ReceiptPageSlide.getInstance());
        pageSlides.add(HealthyFoodPageSlide.getInstance());
        pageSlides.add(EvolutionPageSlide.getInstance());
        pageSlides.add(IngredientsPageSlide.getInstance());
        return pageSlides;
    }
}
