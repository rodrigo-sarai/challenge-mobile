package br.com.modal.service;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.BitmapRequestListener;

public abstract class ImageLoaderService{

    public static void getImage(String imageURL, BitmapRequestListener requestListener){

        AndroidNetworking.get(imageURL)
                .setTag("ImageLoaderService_getImage")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsBitmap(requestListener);
    }
}
