package win.hgfdodo.integration.direct.channel.inthread.send.handler.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.interceptor.WireTap;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import win.hgfdodo.integration.direct.channel.inthread.send.handler.service.String2Service;
import win.hgfdodo.integration.direct.channel.inthread.send.handler.service.StringService;

/**
 * @author Guangfu He
 * @date 2020/11/9 14:45
 * @email hgfkeep@gmail.com
 */
@Configuration
@EnableIntegration
public class Config {
    private final static Logger log = LoggerFactory.getLogger(Config.class);

    @Bean("source")
    public MessageChannel messageChannel() {
        PublishSubscribeChannel publishSubscribeChannel = new PublishSubscribeChannel();
        publishSubscribeChannel.setApplySequence(true);
        return publishSubscribeChannel;
    }

    @Bean
    @ServiceActivator(inputChannel = "source")
    public StringService stringMessageHandler() {
        return new StringService();
    }

    @Bean
    @ServiceActivator(inputChannel = "source")
    public String2Service string2MessageHandler() {
        return new String2Service();
    }

    @GlobalChannelInterceptor
    public ChannelInterceptor channelInterceptor() {
        WireTap wireTap = new WireTap("source");
        return wireTap;
    }

}
