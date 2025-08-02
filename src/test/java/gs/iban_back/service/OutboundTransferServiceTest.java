package gs.iban_back.service;

import gs.iban_back.persistence.repos.OutboundTransferRepository;
import gs.iban_back.rest.dto.Iban;
import gs.iban_back.rest.dto.Transfer;
import gs.iban_back.service.md.CustomIbanService;
import gs.iban_back.service.md.IbanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class OutboundTransferServiceTest {

    @Mock
    IbanService ibanService;
    @Mock
    OutboundTransferRepository repo;

    @Mock
    Iban ibanMock;

    @Test
    public void transferOutbound_shouldThrowIllegalArgumentException() {
        final var service = new OutboundTransferService(ibanService, repo);

        when(ibanService.getIbanData(anyString())).thenReturn(ibanMock);
        when(ibanMock.valid()).thenReturn(false);
        when(ibanMock.messages()).thenReturn(new String[] {"SomeMessage"});

        assertThrows(IllegalArgumentException.class, () -> {
            service.transferOutbound(new Transfer("DE12345678901234567890", BigDecimal.TEN, "EUR"));
        });
    }

    @Test
    public void transferOutbound_shouldStoreTransfer() {
        final var customIbanService = new CustomIbanService();
        final var service = new OutboundTransferService(customIbanService, repo);

        service.transferOutbound(new Transfer("DE94680501011234567890", BigDecimal.TEN, "EUR"));

        verify(repo).save(argThat(arg ->
                arg.getIban().equals("DE94680501011234567890")
                && arg.getAmount().equals(BigDecimal.TEN)
                && arg.getCurrency().equals("EUR")
        ));
    }
}