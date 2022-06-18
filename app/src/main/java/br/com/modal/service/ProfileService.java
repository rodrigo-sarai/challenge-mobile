package br.com.modal.service;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.ParsedRequestListener;

import br.com.modal.core.Utils;
import br.com.modal.dto.ProfileDTO;

public abstract class ProfileService{
    private static final String METHOD_URI = Utils.WEB_SERVICE_URI + "api/v1/user/profile";

    public static void getUserProfile(String loginToken, ParsedRequestListener<ProfileDTO> requestListener){

        AndroidNetworking.get(METHOD_URI)
                .addQueryParameter("token", loginToken)
                .setTag("ProfileService_getUserProfile")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ProfileDTO.class, requestListener);
    }
}
