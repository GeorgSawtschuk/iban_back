package gs.iban_back.rest.dto;

/*
{
  "valid": true, 		// either true or false
  "messages": [ 		// states the reason if "valid" is false
    "Bank code valid: 37040044"
  ],
  "iban": "DE89370400440532013000",	// the IBAN which was to be validated
  "bankData": {
    "bankCode": "37040044",
    "name": "Commerzbank",
    "zip": "50447",
    "city": "Köln",
    "bic": "COBADEFF3701"
  },
  "checkResults": {
    "bankCode": true 		// info about your additional check requests
  }
}
 */

import lombok.Builder;

@Builder
public record Iban(boolean valid,
                   String[] messages,
                   String iban,
                   BankData bankData,
                   CheckResults checkResults) {
}
