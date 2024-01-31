package ru.yandex.tonychem.beautifulcodesberproject.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.tonychem.beautifulcodesberproject.model.ValidationRequest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
public class BracketsValidationControllerIntegrationTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    private final ValidationRequest incorrectRequest = new ValidationRequest("");
    private final ValidationRequest validCorrectRequest = new ValidationRequest("any text is valid");
    private final ValidationRequest invalidCorrectRequest = new ValidationRequest("((incorrect brace order");

    @Test
    public void shouldFailWhenTextIsEmpty() throws Exception {
        mvc.perform(post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(incorrectRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnTrueWhenRequestIsValid() throws Exception {
        mvc.perform(post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validCorrectRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isCorrect", is(true)));
    }

    @Test
    public void shouldReturnFalseWhenRequestIsInvalid() throws Exception {
        mvc.perform(post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(invalidCorrectRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isCorrect", is(false)));
    }
}
