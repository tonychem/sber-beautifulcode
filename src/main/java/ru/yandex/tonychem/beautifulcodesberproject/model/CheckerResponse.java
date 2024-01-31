package ru.yandex.tonychem.beautifulcodesberproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class CheckerResponse {

    @Schema(
            description = "Результат проверки текста"
    )
    private Boolean isCorrect;

    public CheckerResponse(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    @JsonProperty(value = "isCorrect")
    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }
}
