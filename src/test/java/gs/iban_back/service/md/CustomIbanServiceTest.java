package gs.iban_back.service.md;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomIbanServiceTest {

    @Test
    public void getIbanData_shouldReturnWrongFormat() {
        CustomIbanService service = new CustomIbanService();

        final var ibanData = service.getIbanData("12345");

        assertNotNull(ibanData);
        assertFalse(ibanData.valid());
        assertNotNull(ibanData.messages());
        assertEquals(1, ibanData.messages().length);
        assertEquals(CustomIbanService.MESSAGE_WRONG_FORMAT, ibanData.messages()[0]);
    }

    @Test
    public void getIbanData_shouldReturnIbanEmpty() {
        CustomIbanService service = new CustomIbanService();

        final var ibanData = service.getIbanData("");

        assertNotNull(ibanData);
        assertFalse(ibanData.valid());
        assertNotNull(ibanData.messages());
        assertEquals(1, ibanData.messages().length);
        assertEquals(CustomIbanService.MESSAGE_IBAN_EMPTY, ibanData.messages()[0]);
    }

    @Test
    public void getIbanData_shouldReturnUnknown() {
        CustomIbanService service = new CustomIbanService();

        final var ibanData = service.getIbanData("DE12345678901234567890");

        assertNotNull(ibanData);
        assertFalse(ibanData.valid());
        assertNotNull(ibanData.messages());
        assertEquals(1, ibanData.messages().length);
        assertEquals(CustomIbanService.MESSAGE_UNKNOWN_IBAN, ibanData.messages()[0]);
    }

    @Test
    public void getIbanData_shouldReturnIban() {
        CustomIbanService service = new CustomIbanService();

        final var ibanData = service.getIbanData("DE94680501011234567890");

        assertNotNull(ibanData);
        assertTrue(ibanData.valid()); //valid!
        assertNotNull(ibanData.messages());
        assertEquals(0, ibanData.messages().length);
    }

}