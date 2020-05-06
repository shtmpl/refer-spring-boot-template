package refer.spring.boot.template.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;
import refer.spring.boot.template.domain.system.RuntimeConfiguration;

import java.util.Optional;

public interface RuntimeConfigurationRepository extends JpaRepository<RuntimeConfiguration, Long> {

    Optional<RuntimeConfiguration> findByParam(String param);
}
