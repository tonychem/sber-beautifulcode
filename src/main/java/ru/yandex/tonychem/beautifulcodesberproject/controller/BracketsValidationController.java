package ru.yandex.tonychem.beautifulcodesberproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.tonychem.beautifulcodesberproject.model.CheckerResponse;
import ru.yandex.tonychem.beautifulcodesberproject.model.ValidationRequest;
import ru.yandex.tonychem.beautifulcodesberproject.service.BracketsValidationService;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Контроллер валидации скобочной последовательности")
public class BracketsValidationController {

    private final BracketsValidationService bracketsValidationService;

    public BracketsValidationController(BracketsValidationService bracketsValidationService) {
        this.bracketsValidationService = bracketsValidationService;
    }

    @PostMapping("/api/checkBrackets")
    @Operation(
            summary = "Проверяет скобочную последовательность в тексте"
    )
    public ResponseEntity<CheckerResponse> checkBrackets(@RequestBody @Valid ValidationRequest request) {
        CheckerResponse response = bracketsValidationService.validate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
