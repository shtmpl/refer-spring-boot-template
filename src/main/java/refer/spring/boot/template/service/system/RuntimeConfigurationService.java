package refer.spring.boot.template.service.system;

import refer.spring.boot.template.domain.system.RuntimeConfiguration;

import java.util.Optional;

public interface RuntimeConfigurationService {

    Optional<RuntimeConfiguration> findRuntimeConfiguration(String param);
}
