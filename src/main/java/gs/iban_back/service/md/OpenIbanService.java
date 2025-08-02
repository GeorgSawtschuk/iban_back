package gs.iban_back.service.md;

import gs.iban_back.rest.openiban.OpenIbanClient;
import gs.iban_back.rest.dto.Iban;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@ToString
@Service
@Profile("openiban")
public class OpenIbanService implements IbanService {

    private final OpenIbanClient openIbanClient;

    public Iban getIbanData(String iban) {
        return openIbanClient.validate(iban, true, false);
    }
}
