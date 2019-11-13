package refer.spring.boot.operation.service;

import refer.spring.boot.operation.domain.Operation;

import java.util.List;
import java.util.Optional;

public interface OperationService {

    List<Operation> findOperations();

    Optional<Operation> findOperation(Long id);

    Operation saveOperation(Operation operation);

    Operation updateOperation(Long id, Operation operation);
}
