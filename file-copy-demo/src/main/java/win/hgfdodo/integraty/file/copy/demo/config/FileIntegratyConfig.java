package win.hgfdodo.integraty.file.copy.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.file.transformer.FileToByteArrayTransformer;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import win.hgfdodo.integraty.file.copy.demo.service.Handler;

import java.io.File;

/**
 * dir -> filesIn -> bytes -> fielsOut -> dir
 *
 *
 * @author Guangfu He
 * @date 2020/11/3 16:06
 * @email hgfkeep@gmail.com
 */
@Configuration
@EnableConfigurationProperties(Application.class)
@EnableIntegration
public class FileIntegratyConfig {
    private final static Logger log = LoggerFactory.getLogger(FileIntegratyConfig.class);

    @Bean
    public MessageChannel bytes() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel string() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel filesIn() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel filesOut() {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(channel = "filesIn", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileMessageSource(Application application) {
        FileReadingMessageSource fileReadingMessageSource = new FileReadingMessageSource();
        fileReadingMessageSource.setDirectory(new File(application.getInputDir()));
        fileReadingMessageSource.setUseWatchService(true);
        return fileReadingMessageSource;
    }

    @Bean
    @Profile("bytes")
    @Transformer(inputChannel = "filesIn", outputChannel = "bytes")
    public FileToByteArrayTransformer byteArrayTransformer() {
        FileToByteArrayTransformer fileToByteArrayTransformer = new FileToByteArrayTransformer();
        fileToByteArrayTransformer.setDeleteFiles(true);
        return fileToByteArrayTransformer;
    }


    @Bean
    @Profile("string")
    @Transformer(inputChannel = "filesIn", outputChannel = "string")
    public FileToStringTransformer stringTransformer() {
        FileToStringTransformer fileToStringTransformer = new FileToStringTransformer();
        fileToStringTransformer.setDeleteFiles(true);
        return fileToStringTransformer;
    }

    @Bean
    @Profile("bytes")
    @ServiceActivator(inputChannel = "bytes", outputChannel = "filesOut")
    public Handler bytesHandler() {
        return new Handler();
    }

    @Bean
    @Profile("string")
    @ServiceActivator(inputChannel = "string", outputChannel = "filesOut")
    public Handler stringHandler() {
        return new Handler();
    }

    @Bean
    @ServiceActivator(inputChannel = "filesOut")
    public MessageHandler messageHandler(Application application) {
        FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(new File(application.getOutputDir()));
        fileWritingMessageHandler.setFileExistsMode(FileExistsMode.REPLACE);
        fileWritingMessageHandler.setAutoCreateDirectory(true);
        fileWritingMessageHandler.setExpectReply(false);
        return fileWritingMessageHandler;
    }

}
