package refer.spring.boot.template.domain;

public class RuntimeConfigurationNotFoundException extends ResourceNotFoundException {

    public RuntimeConfigurationNotFoundException() {
        super();
    }

    public RuntimeConfigurationNotFoundException(String message) {
        super(message);
    }

    public RuntimeConfigurationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeConfigurationNotFoundException(Throwable cause) {
        super(cause);
    }
}
