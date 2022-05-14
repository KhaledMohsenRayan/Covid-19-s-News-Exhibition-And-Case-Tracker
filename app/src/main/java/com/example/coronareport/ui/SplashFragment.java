package com.example.coronareport.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coronareport.R;
import com.example.coronareport.helperClasses.Render;
import com.example.coronareport.helperClasses.Zoom;
import com.example.coronareport.databinding.FragmentSplashBinding;


public class SplashFragment extends Fragment {

    private FragmentSplashBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        binding = FragmentSplashBinding.inflate(inflater);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                continueTo(flash(binding.ivSplash) , 1000L);
            }
        } , 2500);
    }
    private AnimatorSet flash(View view){
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f, 0f, 1f);
        animatorSet.playTogether(objectAnimator);
        return animatorSet;
    }
    private void continueTo(AnimatorSet animation, Long duration) {
        animate(animation , duration);
        dismissLoading(duration);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animate(animation, duration);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        navController.navigate(R.id.action_splashFragment_to_globalFragment);
                    }
                } , duration + 100);
            }
        } , duration + 100);
    }

    private void animate(AnimatorSet animation, Long duration) {
        Render render = new Render(getActivity());
        render.setDuration(duration);
        render.setAnimation(animation);
        render.start();
    }

    private void dismissLoading(Long duration) {
        Render render = new Render(getActivity());
        render.setDuration(duration);
        render.setAnimation(Zoom.Out(binding.ivSplashSpin));
        render.start();
    }

}