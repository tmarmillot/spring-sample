package royleej9.junit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringBootJunitApplication {

	public static void main(String[] args) throws Exception {
	    
	    log.info("Before App start- long job.........................................");
        Thread.sleep(60 * 1000 * 5);
        
        log.info("Start App !!!!!!!!!");
	    SpringApplication.run(SpringBootJunitApplication.class, args);
	    log.info("Started App success!!!!!!!!!");
	}

}
