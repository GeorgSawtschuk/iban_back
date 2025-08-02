package gs.iban_back.rest.controller.md;

import gs.iban_back.service.md.OpenIbanService;
import gs.iban_back.rest.openiban.OpenIbanClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(IbanController.class)
@ActiveProfiles("openiban")
@Import(OpenIbanService.class)
class IbanControllerOpenApiTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private OpenIbanClient openIbanClient;

    @Test
    public void getIbanData_shouldCallOpenApi() throws Exception {
        var response = mvc.perform(get("/iban/DE89603501301234567890"));

        verify(openIbanClient)
                .validate(
                        eq("DE89603501301234567890"),
                        eq(true),
                        eq(false));

    }



}