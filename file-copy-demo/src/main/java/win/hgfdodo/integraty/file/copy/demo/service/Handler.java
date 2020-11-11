package win.hgfdodo.integraty.file.copy.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author Guangfu He
 * @date 2020/11/3 16:25
 * @email hgfkeep@gmail.com
 */
public class Handler {
    private final static Logger log = LoggerFactory.getLogger(Handler.class);

    public String handleString(String input) {
        System.out.println("Copying text: " + input);
        return input.toUpperCase();
    }

    public File handleFile(File input) {
        System.out.println("Copying file: " + input.getAbsolutePath());
        return input;
    }
//      同参数类型的函数会造成 ServiceActivator
//    public byte[] handleByte2(byte[] input){
//        System.out.println("Handle byte2  " + input.length + " bytes ...");
//        return new String(input).toLowerCase().getBytes();
//    }

    public byte[] handleBytes(byte[] input) {
        System.out.println("Copying " + input.length + " bytes ...");
        return new String(input).toUpperCase().getBytes();
    }

}
