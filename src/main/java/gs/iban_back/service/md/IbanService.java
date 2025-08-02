package gs.iban_back.service.md;

import gs.iban_back.rest.dto.Iban;

public interface IbanService {
    public Iban getIbanData(String iban);
}
