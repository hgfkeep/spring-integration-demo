package win.hgfdodo.integraty.basic.file.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;


/**
 * @author Guangfu He
 * @date 2020/11/2 16:12
 * @email hgfkeep@gmail.com
 */
@Configuration
@EnableIntegration
@EnableConfigurationProperties(Application.class)
public class FileIntegratyConfig {
    private final static Logger log = LoggerFactory.getLogger(FileIntegratyConfig.class);

    @Autowired
    Application application;

    @Bean
    public MessageChannel fileChannel() {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(value = "fileChannel", poller = @Poller(fixedDelay = "1000", maxMessagesPerPoll = "3"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource sourceReader = new FileReadingMessageSource();
        sourceReader.setDirectory(new File(application.getInputDir()));
        sourceReader.setUseWatchService(true); // can recursive directories!
        sourceReader.setFilter(new SimplePatternFileListFilter(application.getFilePattern()));
        return sourceReader;
    }

    @Bean
    @ServiceActivator(inputChannel = "fileChannel")
    public MessageHandler fileWritingMessageHandler() {
        log.debug("file writung handler constructor");
        FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(new File(application.getOutputDir()));
        fileWritingMessageHandler.setFileExistsMode(FileExistsMode.REPLACE);
        fileWritingMessageHandler.setAutoCreateDirectory(true);
        fileWritingMessageHandler.setExpectReply(false);
        return fileWritingMessageHandler;
    }

}
