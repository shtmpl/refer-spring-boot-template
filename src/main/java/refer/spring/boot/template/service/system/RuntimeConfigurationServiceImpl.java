package refer.spring.boot.template.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refer.spring.boot.template.domain.system.RuntimeConfiguration;
import refer.spring.boot.template.repository.system.RuntimeConfigurationRepository;

import java.util.Optional;

@Transactional
@Service
public class RuntimeConfigurationServiceImpl implements RuntimeConfigurationService {

    private final RuntimeConfigurationRepository runtimeConfigurationRepository;

    @Autowired
    public RuntimeConfigurationServiceImpl(RuntimeConfigurationRepository runtimeConfigurationRepository) {
        this.runtimeConfigurationRepository = runtimeConfigurationRepository;
    }

    @Override
    public Optional<RuntimeConfiguration> findRuntimeConfiguration(String param) {
        return runtimeConfigurationRepository.findByParam(param);
    }
}
