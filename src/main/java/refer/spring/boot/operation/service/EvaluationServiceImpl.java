package refer.spring.boot.operation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refer.spring.boot.operation.domain.EvaluationException;
import refer.spring.boot.operation.domain.Operation;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Transactional
@Service
public class EvaluationServiceImpl implements EvaluationService {

    private final OperationService operationService;

    @Autowired
    public EvaluationServiceImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public Long evaluate(Long first, Long other) {
        Operation operation = new Operation();
        operation.setInitiatedAt(OffsetDateTime.now());

        Operation initiated = operationService.saveOperation(new Operation());

        Long result;
        try {
            result = calculate(first, other);

            initiated.setOutcome(Operation.Outcome.SUCCESS);
        } catch (Throwable throwable) {
            initiated.setOutcome(Operation.Outcome.FAILURE);
            operation.setReason(throwable.getMessage());

            throw new EvaluationException(throwable);
        } finally {
            initiated.setCompletedAt(OffsetDateTime.now());
        }

        operationService.updateOperation(initiated.getId(), initiated);

        return result;
    }

    @Override
    public Operation evaluateAsync(Long first, Long other) {
        Operation operation = new Operation();
        operation.setInitiatedAt(OffsetDateTime.now());

        Operation initiated = operationService.saveOperation(operation);

        CompletableFuture.supplyAsync(() -> calculate(first, other))
                .handle((Long value, Throwable throwable) -> {
                    Operation completed = new Operation();

                    if (value != null) {
                        completed.setOutcome(Operation.Outcome.SUCCESS);
                    }

                    if (throwable != null) {
                        completed.setOutcome(Operation.Outcome.FAILURE);
                        completed.setReason(throwable.getMessage());
                    }

                    completed.setCompletedAt(OffsetDateTime.now());

                    return operationService.updateOperation(initiated.getId(), completed);
                });

        return initiated;
    }

    private Long calculate(Long first, Long other) {
        Long result = first + other;
        if (first + other == 42) {
            throw new RuntimeException("42!");
        }

        return result;
    }
}
