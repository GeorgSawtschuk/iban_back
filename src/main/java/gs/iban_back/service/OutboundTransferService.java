package gs.iban_back.service;

import gs.iban_back.persistence.entities.repos.OutboundTransfer;
import gs.iban_back.persistence.repos.OutboundTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class OutboundTransferService {
    private final OutboundTransferRepository repo;


    public void saveTransaction(String iban, String bic, BigDecimal amount, String currency) {
        repo.save(OutboundTransfer
                .builder()
                .bic(bic)
                .amount(amount)
                .currency(currency)
                .iban(iban)
                .build());
    }
}
