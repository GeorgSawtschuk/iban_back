package gs.iban_back.service.md;

import gs.iban_back.rest.dto.BankData;
import gs.iban_back.rest.dto.Iban;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@ToString
@Service
@Profile("!openiban")
public class CustomIbanService implements IbanService {

    public static final String MESSAGE_WRONG_FORMAT = "IBAN must be 22 characters long, start with DE following digits";
    public static final String MESSAGE_IBAN_EMPTY = "IBAN cannot be null or empty";
    public static final String MESSAGE_UNKNOWN_IBAN = "Unknown IBAN";
    private final Map<String, BankData> customIbanMap = new HashMap<>();

    public Iban getIbanData(String iban) {
        final var validationError = validateIban(iban);

        if( validationError.isPresent()) {
            return Iban
                    .builder()
                    .valid(false)
                    .messages(new String[] {validationError.get()})
                    .build();
        }
        final var bankData = customIbanMap.get(extractBankId(iban));
        return Iban
                .builder()
                .valid(true)
                .iban(iban)
                .bankData(bankData)
                .messages(new String[] {})
                .build();
    }

    private Optional<String> validateIban(String iban) {
        if (iban == null || iban.isEmpty()) {
            return Optional.of(MESSAGE_IBAN_EMPTY);
        }

        //DE iban started with ISO land code (2 places), following check digits (2 places), following with LBZ (8 digits) and account number (10 digits)
        if (iban.length() != 22 || !iban.matches("^[A-Z]{2}\\d{2}\\d{8}\\d{10}$")) {
            return Optional.of(MESSAGE_WRONG_FORMAT);
        }

        if (customIbanMap.containsKey(extractBankId(iban))) {
            return Optional.empty();
        }

        return Optional.of(MESSAGE_UNKNOWN_IBAN);
    }

    private static String extractBankId(String iban) {
        return iban.substring(0, 12);
    }

    {
        customIbanMap.put("DE8937040044", BankData.builder()
                .bankCode("37040044")
                .bic("COBADEFFXXX")
                .name("Commerzbank")
                .zip("50447")
                .city("Köln")
                .build());
        customIbanMap.put("DE9468050101", BankData.builder()
                .bankCode("68050101")
                .bic("FRSPDE66XXX")
                .name("Sparkasse Freiburg-Nördlicher Breisgau")
                .zip("79098")
                .city("Freiburg im Breisgau")
                .build());
        customIbanMap.put("DE8960350130", BankData.builder()
                .bankCode("60350130")
                .bic("BBKRDE6BXXX")
                .name("Kreissparkasse Böblingen")
                .zip("71005")
                .city("Böblingen")
                .build());
    }

}
