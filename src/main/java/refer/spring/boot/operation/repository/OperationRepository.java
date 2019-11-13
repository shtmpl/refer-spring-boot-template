package refer.spring.boot.operation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import refer.spring.boot.operation.domain.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
