package refer.spring.boot.template.service;

import refer.spring.boot.template.domain.RuntimeConfiguration;

import java.util.Optional;

public interface RuntimeConfigurationService {

    Optional<RuntimeConfiguration> findRuntimeConfiguration(String param);
}
