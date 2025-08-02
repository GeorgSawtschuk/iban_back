package gs.iban_back.persistence.repos;

import gs.iban_back.persistence.entities.repos.OutboundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OutboundTransferRepository extends JpaRepository<OutboundTransfer, UUID> {
    <T> List<T> findAllByOrderByTimestamp();
}
