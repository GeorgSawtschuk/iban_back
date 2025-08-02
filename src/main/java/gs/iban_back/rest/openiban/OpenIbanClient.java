package gs.iban_back.rest.openiban;

import gs.iban_back.rest.dto.Iban;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openiban", url = "${openiban.url}")
public interface OpenIbanClient {

    @GetMapping("/validate/{iban}")
    Iban validate(@PathVariable("iban") String iban,
                  @RequestParam("getBIC") boolean getBic,
                  @RequestParam("validateBankCode") boolean validateBankCode);
}
