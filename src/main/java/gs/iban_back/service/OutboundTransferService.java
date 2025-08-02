package gs.iban_back.service;

import gs.iban_back.persistence.entities.repos.OutboundTransfer;
import gs.iban_back.persistence.repos.OutboundTransferRepository;
import gs.iban_back.rest.dto.Transfer;
import gs.iban_back.service.md.IbanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OutboundTransferService {

    private final IbanService ibanService;
    private final OutboundTransferRepository repo;

    public OutboundTransfer transferOutbound(@Validated Transfer transfer) {

        final var iban = ibanService.getIbanData(transfer.iban());
        if(!iban.valid()) {
            throw new IllegalArgumentException("Iban validation failed! " + (iban.messages().length > 0 ? iban.messages()[0] : ""));
        }

        return saveTransaction(iban.iban(), iban.bankData().bic(), transfer.amount(), transfer.currency());
    }

    public Page<OutboundTransfer> getOutboundTransfers(Pageable pageable) {
        return repo.findAll(pageable);
    }

    private OutboundTransfer saveTransaction(String iban, String bic, BigDecimal amount, String currency) {
        return repo.save(OutboundTransfer
                .builder()
                .bic(bic)
                .amount(amount)
                .currency(currency)
                .iban(iban)
                .build());
    }

    public Optional<OutboundTransfer> getOutboundTransfer(UUID id) {
        return repo.findById(id);
    }
}
