package gs.iban_back.rest.dto;

import lombok.Builder;

@Builder
public record BankData(String bankCode, String name, String zip, String city, String bic) {
}
