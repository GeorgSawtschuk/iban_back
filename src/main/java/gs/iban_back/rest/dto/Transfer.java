package gs.iban_back.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record Transfer(
    @NotBlank(message = "Iban required!")
    String iban,
    @NotNull(message = "amount required")
    BigDecimal amount,
    @Pattern(regexp = "^[A-Z]{3}$", message = "Must be a 3-letter uppercase ISO currency code.")
    String currency
) {
}
