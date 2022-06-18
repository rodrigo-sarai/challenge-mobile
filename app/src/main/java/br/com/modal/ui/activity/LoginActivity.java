package br.com.modal.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;

import br.com.modal.R;
import br.com.modal.core.Utils;
import br.com.modal.databinding.ActivityLoginBinding;
import br.com.modal.dto.ErrorResponseDTO;
import br.com.modal.dto.LoginDTO;
import br.com.modal.service.LoginService;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private final ParsedRequestListener<LoginDTO> loginRequestListener = new ParsedRequestListener<LoginDTO>() {
        @Override
        public void onResponse(LoginDTO loginRequestResponse) {
            Utils.setLoginToken(loginRequestResponse.getToken(), getApplicationContext());
            navigateHome();
        }

        @Override
        public void onError(ANError anError) {

            String errorMessage;

            try {
                ErrorResponseDTO errorResponse = new Gson().fromJson(anError.getErrorBody(), ErrorResponseDTO.class);
                errorMessage = errorResponse.getError_message();
            }catch(Exception e){
                errorMessage = getString(R.string.error_logginin);
            }

            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        emailEditText = binding.loginEditEmail;
        passwordEditText = binding.loginEditSenha;
        loginButton = binding.loginButtonLogin;

        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                executeLogin();
            }
            return false;
        });

        loginButton.setOnClickListener(v -> executeLogin());

        //Se já houver um token de autorização armazenado, chamamos o home da aplicação
        if(!Utils.getLoginToken(this).trim().isEmpty()){
            navigateHome();
        }
    }

    private void executeLogin(){

        Utils.setLoginToken("", getApplicationContext());

        String email = emailEditText.getText().toString();
        String senha = passwordEditText.getText().toString();

        if(email.trim().isEmpty()){
            Toast.makeText(getApplicationContext(), getString(R.string.empty_email), Toast.LENGTH_LONG).show();
            return;
        }

        if(senha.trim().isEmpty()){
            Toast.makeText(getApplicationContext(), getString(R.string.empty_password), Toast.LENGTH_LONG).show();
            return;
        }

        LoginService.login(email, senha, loginRequestListener);
    }

    private void navigateHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

        finish();
    }
}