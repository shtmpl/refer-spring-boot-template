package refer.spring.boot.operation.controller.api.response;

import refer.spring.boot.operation.domain.Operation;

import java.time.OffsetDateTime;

public class ResponseOperation {

    public enum Outcome {

        SUCCESS,
        FAILURE
    }

    private Long id;

    private OffsetDateTime createdAt;

    private OffsetDateTime initiatedAt;

    private OffsetDateTime completedAt;

    private Operation.Outcome outcome;

    private String reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getInitiatedAt() {
        return initiatedAt;
    }

    public void setInitiatedAt(OffsetDateTime initiatedAt) {
        this.initiatedAt = initiatedAt;
    }

    public OffsetDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(OffsetDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Operation.Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Operation.Outcome outcome) {
        this.outcome = outcome;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
