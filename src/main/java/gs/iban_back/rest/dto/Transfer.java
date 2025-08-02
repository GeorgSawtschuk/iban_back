package gs.iban_back.rest.dto;

import java.math.BigDecimal;

public record Transfer(
    String iban,
    String bic,
    BigDecimal amount,
    String currency
) {
}
