package gs.iban_back.rest.controller.md;

import gs.iban_back.service.md.IbanService;
import gs.iban_back.rest.dto.Iban;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class IbanController {

    private final IbanService ibanService;

    @GetMapping(value = "/iban/{iban}", produces = "application/json")
    public ResponseEntity<Iban> getIbanData(@PathVariable String iban){
        return ResponseEntity.ok(ibanService.getIbanData(iban));
    }
}
