package refer.spring.boot.operation.service;

import refer.spring.boot.operation.domain.Operation;

public interface EvaluationService {

    Long evaluate(Long first, Long other);

    Operation evaluateAsync(Long first, Long other);
}
