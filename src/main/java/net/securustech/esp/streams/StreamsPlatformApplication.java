package net.securustech.esp.streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "net.securustech.esp")
@SpringBootApplication (exclude = IntegrationAutoConfiguration.class)
public class StreamsPlatformApplication {


    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsPlatformApplication.class);

    public static void main(String[] args) throws Exception {

        LOGGER.info(System.getProperty("spring.application.name") + " ::: Spring.Profiles.Active :-> " + System.getProperty("spring.profiles.active"));

        SpringApplication.run(StreamsPlatformApplication.class, args);

        LOGGER.info("Started <<streams-platform>> Listener...");
    }

}
