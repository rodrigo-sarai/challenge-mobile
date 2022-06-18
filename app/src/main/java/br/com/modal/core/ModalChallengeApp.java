package br.com.modal.core;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class ModalChallengeApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
