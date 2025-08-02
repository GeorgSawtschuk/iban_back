package gs.iban_back.persistence.entities.repos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutboundTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private UUID id;

    @Column(length = 34)
    @NotNull
    private String iban;

    @Column(length = 11)
    @NotNull
    private String bic;

    @Column(precision = 19, scale = 2)
    @NotNull
    private BigDecimal amount;

    @Column(length = 3)
    @NotNull
    private String currency;

    private Instant timestamp;

    @PrePersist
    private void onPrePersist() {
        timestamp = Instant.now();
    }

}
