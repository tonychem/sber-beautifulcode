package ru.yandex.tonychem.beautifulcodesberproject.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public class ValidationRequest {
    @NotEmpty
    @Schema(
            description = "Входящее сообщение"
    )
    private String text;

    public ValidationRequest() {
    }

    public ValidationRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
