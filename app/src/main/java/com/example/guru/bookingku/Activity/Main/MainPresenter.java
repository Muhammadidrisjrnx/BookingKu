package com.example.guru.bookingku.Activity.Main;

import com.example.guru.bookingku.Activity.Base.PresenterActivity;
import com.example.guru.bookingku.Fragment.Base.BaseFragment;
import com.example.guru.bookingku.Fragment.Home.HomeFragment;
import com.example.guru.bookingku.Fragment.Profile.ProfileFragment;

public class MainPresenter implements PresenterActivity<MainView> {

    private MainView view;
    private final BaseFragment homeFragment = new HomeFragment();
    private final BaseFragment profileFragment = new ProfileFragment();
    private BaseFragment currentFragment = null;

    @Override
    public void onAttach(MainView view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        view = null;
    }

    public void showHomeFragmentForFirstTime(){
        currentFragment = homeFragment;
        view.attachHomeFragment(currentFragment, homeFragment);
    }
    public void navigateCurrentFragmentToHomeFragment(){
        view.attachHomeFragment(currentFragment, homeFragment);
        currentFragment = homeFragment;
    }
    public void navigateCurrentFragmentToProfileFragment(){
        view.attachProfileFragment(currentFragment, profileFragment);
        currentFragment = profileFragment;
    }
}
