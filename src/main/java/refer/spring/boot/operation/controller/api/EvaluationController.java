package refer.spring.boot.operation.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import refer.spring.boot.operation.controller.api.request.RequestEvaluation;
import refer.spring.boot.operation.domain.EvaluationException;
import refer.spring.boot.operation.domain.Operation;
import refer.spring.boot.operation.service.EvaluationService;

import javax.validation.Valid;

@RequestMapping("/api/evaluation")
@RestController
public class EvaluationController {

    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("")
    public ResponseEntity<?> evaluate(@RequestParam(defaultValue = "false") Boolean async,
                                      @Valid @RequestBody RequestEvaluation request) {
        return async ?
                evaluateAsync(request) :
                evaluate(request);
    }

    private ResponseEntity<Long> evaluate(RequestEvaluation request) {
        Long first = request.getFirst();
        Long other = request.getOther();
        Long result = evaluationService.evaluate(first, other);

        return ResponseEntity.ok(result);
    }

    private ResponseEntity<Operation> evaluateAsync(RequestEvaluation request) {
        Long first = request.getFirst();
        Long other = request.getOther();
        Operation result = evaluationService.evaluateAsync(first, other);

        return ResponseEntity.ok(result);
    }
}
