package win.hgfdodo.integration.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.xml.LoggingChannelAdapterParser;

/**
 * @author Guangfu He
 * @date 2020/11/4 20:46
 * @email hgfkeep@gmail.com
 */
@Profile("logger")
@Configuration
@EnableIntegration
public class IntegrationLoggerConfig {
    private final static Logger log = LoggerFactory.getLogger(IntegrationLoggerConfig.class);


    public MessageSource messageSource() {

    }

}
