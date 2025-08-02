package gs.iban_back.rest.controller.md;

import gs.iban_back.service.md.CustomIbanService;
import gs.iban_back.rest.dto.Iban;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("custom")
@WebMvcTest(IbanController.class)
@Import(CustomIbanService.class)
public class IbanControllerCustomTest {

    @Autowired
    private MockMvcTester mvc;

    @Test
    public void getIbanData_shouldReturnIbanData(){
        var response = mvc.perform(get("/iban/DE89603501301234567890"));

        assertThat(response)
                .hasStatus(HttpStatus.OK)
                .hasContentType(MediaType.APPLICATION_JSON_VALUE)
                .bodyJson()
                .convertTo(Iban.class)
                .extracting("valid", "bankData.bic")
                .containsExactly(true, "BBKRDE6BXXX");

    }

    @Test
    public void getIbanData_shouldReturnUnknownIban(){
        var response = mvc.perform(get("/iban/DE77703501301234567890"));

        assertThat(response)
                .hasStatus(HttpStatus.OK)
                .hasContentType(MediaType.APPLICATION_JSON_VALUE)
                .bodyJson()
                .convertTo(Iban.class)
                .extracting("valid", "messages" )
                .containsExactly(false, new String[] {CustomIbanService.MESSAGE_UNKNOWN_IBAN});

    }

}