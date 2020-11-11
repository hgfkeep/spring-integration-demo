package win.hgfdodo.integraty.basic.file.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import win.hgfdodo.integraty.basic.file.demo.config.Application;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class FileDemoApplication implements CommandLineRunner {

    @Autowired
    Application application;

    public static void main(String[] args) {
        SpringApplication.run(FileDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SimplePatternFileListFilter fileListFilter = new SimplePatternFileListFilter(application.getFilePattern());
        File dir = new File(application.getInputDir());
        List<File> files = fileListFilter.filterFiles(dir.listFiles());
        System.out.println(files);
    }
}
