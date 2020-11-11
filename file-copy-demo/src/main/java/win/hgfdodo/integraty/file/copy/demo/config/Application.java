package win.hgfdodo.integraty.file.copy.demo.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Guangfu He
 * @date 2020/11/3 16:06
 * @email hgfkeep@gmail.com
 */
@ConfigurationProperties("integraty.file")
@Data
public class Application {
    private final static Logger log = LoggerFactory.getLogger(Application.class);


    private String inputDir;

    private String outputDir;

}
