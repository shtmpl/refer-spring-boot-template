package refer.spring.boot.operation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refer.spring.boot.operation.domain.Operation;
import refer.spring.boot.operation.repository.OperationRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public List<Operation> findOperations() {
        return operationRepository.findAll();
    }

    @Override
    public Optional<Operation> findOperation(Long id) {
        return operationRepository.findById(id);
    }

    @Override
    public Operation saveOperation(Operation operation) {
        Operation result = new Operation();
        result.setCreatedAt(OffsetDateTime.now());
        result.setInitiatedAt(operation.getInitiatedAt());
        result.setCompletedAt(operation.getCompletedAt());
        result.setOutcome(operation.getOutcome());
        result.setReason(operation.getReason());

        return operationRepository.save(result);
    }

    @Override
    public Operation updateOperation(Long id, Operation operation) {
        Operation found = operationRepository.findById(id)
                .orElse(null);
        if (found == null) {
            return saveOperation(operation);
        }

        OffsetDateTime initiatedAt = operation.getInitiatedAt();
        if (initiatedAt != null && !initiatedAt.equals(found.getInitiatedAt())) {
            found.setInitiatedAt(initiatedAt);
        }

        OffsetDateTime completedAt = operation.getCompletedAt();
        if (completedAt != null && !completedAt.equals(found.getCompletedAt())) {
            found.setCompletedAt(completedAt);
        }

        Operation.Outcome outcome = operation.getOutcome();
        if (outcome != null && !outcome.equals(found.getOutcome())) {
            found.setOutcome(outcome);
        }

        String reason = operation.getReason();
        if (outcome == Operation.Outcome.SUCCESS) {
            found.setReason(null);
        } else {
            if (reason != null && !reason.equals(found.getReason())) {
                found.setReason(reason);
            }
        }

        return operationRepository.save(found);
    }
}
