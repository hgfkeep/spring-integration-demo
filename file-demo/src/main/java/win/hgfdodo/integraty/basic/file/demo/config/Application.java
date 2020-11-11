package win.hgfdodo.integraty.basic.file.demo.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Guangfu He
 * @date 2020/11/2 16:20
 * @email hgfkeep@gmail.com
 */
@ConfigurationProperties(prefix = "integraty.file")
@Data
public class Application {
    private final static Logger log = LoggerFactory.getLogger(Application.class);

    private String inputDir;
    private String outputDir;
    private String filePattern;
}
