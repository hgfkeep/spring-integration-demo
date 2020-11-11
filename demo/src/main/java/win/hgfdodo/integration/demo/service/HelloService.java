package win.hgfdodo.integration.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Guangfu He
 * @date 2020/11/4 20:27
 * @email hgfkeep@gmail.com
 */
public class HelloService {
    private final static Logger log = LoggerFactory.getLogger(HelloService.class);

    /**
     * 对于任意name打招呼
     *
     * @param name 人名
     * @return 打招呼
     */
    public String hello(String name) {
        return "hello, " + name;
    }

}
