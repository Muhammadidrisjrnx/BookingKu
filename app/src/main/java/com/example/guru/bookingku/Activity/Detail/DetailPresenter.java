package com.example.guru.bookingku.Activity.Detail;

import com.example.guru.bookingku.Activity.Base.PresenterActivity;
import com.example.guru.bookingku.Model.Item;


public class DetailPresenter implements PresenterActivity<DetailView> {

    Item item;
    DetailView view;
    boolean isItemNull = item == null;

    @Override
    public void onAttach(DetailView view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        view = null;
    }

    public void setItem(Item item){
        this.item = item;
        view.showDetailItem(this.item);
    }
    public void onAddCartClicked(){
        if(item != null)
            view.startBookingActivity(item);
        else{
            view.showError(String.valueOf(isItemNull));
        }
    }
}
