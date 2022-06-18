package br.com.modal.service;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.ParsedRequestListener;

import br.com.modal.core.Utils;
import br.com.modal.dto.LoginDTO;

public abstract class LoginService{
    private static final String METHOD_URI = Utils.WEB_SERVICE_URI + "api/v1/auth";

    public static void login(String email, String password, ParsedRequestListener<LoginDTO> requestListener){

        AndroidNetworking.post(METHOD_URI)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .setTag("LoginService_login")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(LoginDTO.class, requestListener);
    }
}
