package im.vinci;

import im.vinci.website.config.ControllerConfiguration;
import im.vinci.website.config.DaoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * 启动项
 * Created by peng@ting on 16/1/6.
 */
@SpringBootApplication
@Import({
        ControllerConfiguration.class,
        DaoConfiguration.class
})
public class VinciApplication {
    public static void main(String[] args) {
        SpringApplication.run(VinciApplication.class, args);
    }


}
