package ru.yandex.tonychem.beautifulcodesberproject.service;

import org.springframework.stereotype.Service;
import ru.yandex.tonychem.beautifulcodesberproject.model.CheckerResponse;
import ru.yandex.tonychem.beautifulcodesberproject.model.ValidationRequest;

@Service
public class BracketsValidationService {

    public CheckerResponse validate(ValidationRequest request) {
        return new CheckerResponse(
                BracketUtils.validateBrackets(request.getText())
        );
    }
}
