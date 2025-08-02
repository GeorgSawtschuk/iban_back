package gs.iban_back.persistence.repos;

import gs.iban_back.persistence.entities.repos.OutboundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboundTransferRepository extends JpaRepository<OutboundTransfer, Long> {
    <T> List<T> findAllByOrderByTimestamp();
}
