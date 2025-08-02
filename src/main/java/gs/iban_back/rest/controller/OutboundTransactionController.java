package gs.iban_back.rest.controller;

import gs.iban_back.persistence.entities.repos.OutboundTransfer;
import gs.iban_back.rest.dto.AppResponse;
import gs.iban_back.rest.dto.Transfer;
import gs.iban_back.service.OutboundTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transfer")
public class OutboundTransactionController {

    private final OutboundTransferService service;

    @PostMapping
    public ResponseEntity<AppResponse<OutboundTransfer>> createTransfer(@RequestBody Transfer transfer) {
        return ResponseEntity.ok(
                AppResponse
                        .<OutboundTransfer>builder()
                        .data(service.transferOutbound(transfer))
                        .success(true)
                        .build());
    }

    @GetMapping
    public Page<OutboundTransfer> getOutboundTransfers(Pageable pageable) {
        return service.getOutboundTransfers(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutboundTransfer> getOutboundTransfers(@PathVariable UUID id) {
        return service.getOutboundTransfer(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
