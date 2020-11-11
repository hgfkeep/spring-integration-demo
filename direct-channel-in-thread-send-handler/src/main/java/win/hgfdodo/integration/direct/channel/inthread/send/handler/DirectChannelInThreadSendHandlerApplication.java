package win.hgfdodo.integration.direct.channel.inthread.send.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
public class DirectChannelInThreadSendHandlerApplication implements CommandLineRunner {

    @Autowired
    @Qualifier("source")
    MessageChannel messageChannel;

    public static void main(String[] args) {
        SpringApplication.run(DirectChannelInThreadSendHandlerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            messageChannel.send(new GenericMessage<>("hello, " + i));
        }
        System.out.println("end!");
    }
}
