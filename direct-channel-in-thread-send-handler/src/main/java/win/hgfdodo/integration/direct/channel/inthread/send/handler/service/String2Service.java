package win.hgfdodo.integration.direct.channel.inthread.send.handler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Guangfu He
 * @date 2020/11/9 15:44
 * @email hgfkeep@gmail.com
 */
public class String2Service {
    private final static Logger log = LoggerFactory.getLogger(String2Service.class);

    public void handler(String msg) {
        log.debug("Request to handler message: {}", msg);
        System.out.println(msg);
    }

//    public void throwHandler(String msg) {
//        log.debug("Request to handler message: {}", msg);
//        throw new RuntimeException("msg: " + msg);
//    }
}
