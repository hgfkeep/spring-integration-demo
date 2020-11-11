package win.hgfdodo.integration.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;
import win.hgfdodo.integration.demo.service.HelloService;

/**
 * @author Guangfu He
 * @date 2020/11/4 19:59
 * @email hgfkeep@gmail.com
 */
@Profile("hello")
@Configuration
@EnableIntegration
public class IntegrationConfig {
    private final static Logger log = LoggerFactory.getLogger(IntegrationConfig.class);

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public QueueChannel outputChannel() {
        return new QueueChannel(10);
    }

    @Bean
    @ServiceActivator(inputChannel = "inputChannel", outputChannel = "outputChannel")
    public HelloService helloService() {
        return new HelloService();
    }

}
