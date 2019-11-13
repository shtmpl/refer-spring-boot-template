package refer.spring.boot.template.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import refer.spring.boot.template.controller.api.response.ResponseVersion;
import refer.spring.boot.template.domain.RuntimeConfiguration;
import refer.spring.boot.template.domain.RuntimeConfigurationNotFoundException;
import refer.spring.boot.template.domain.RuntimeConfigurations;
import refer.spring.boot.template.service.RuntimeConfigurationService;

@RequestMapping("/api/version")
@RestController
public class VersionController {

    private final RuntimeConfigurationService runtimeConfigurationService;

    @Value("${git.commit.id}")
    private String gitCommitId;

    @Autowired
    public VersionController(RuntimeConfigurationService runtimeConfigurationService) {
        this.runtimeConfigurationService = runtimeConfigurationService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseVersion> show() {
        String applicationName = runtimeConfigurationService.findRuntimeConfiguration(RuntimeConfigurations.PARAM_APPLICATION_NAME)
                .map(RuntimeConfiguration::getStringValue)
                .orElseThrow(() ->
                        new RuntimeConfigurationNotFoundException(String.format("No runtime configuration found for param: %s", RuntimeConfigurations.PARAM_APPLICATION_NAME)));

        ResponseVersion result = new ResponseVersion();
        result.setApplicationName(applicationName);
        result.setApplicationVersion(gitCommitId);

        return ResponseEntity.ok(result);
    }
}
