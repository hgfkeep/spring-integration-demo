package win.hgfdodo.integration.direct.channel.inthread.send.handler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Guangfu He
 * @date 2020/11/9 14:47
 * @email hgfkeep@gmail.com
 */
public class StringService {
    private final static Logger log = LoggerFactory.getLogger(StringService.class);

    public void handler(String msg) {
        log.debug("Request to handler message: {}", msg);
        System.out.println(msg);
    }

}
