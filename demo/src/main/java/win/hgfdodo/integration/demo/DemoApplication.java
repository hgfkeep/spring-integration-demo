package win.hgfdodo.integration.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    @Qualifier("inputChannel")
    MessageChannel inputChannel;

    @Autowired
    @Qualifier("outputChannel")
    PollableChannel outputChannel;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        inputChannel.send(new GenericMessage<String>("workd"));
        System.out.println(outputChannel.receive(0));
    }
}
