package gs.iban_back.rest.openiban.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpenIbanParams {
    private boolean BIC;
    private boolean validateBankCode;
}