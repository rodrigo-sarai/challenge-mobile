package br.com.modal.dto;

public class ErrorResponseDTO {
    private boolean success;
    private String error_message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
