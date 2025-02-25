package mysite;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MySiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySiteApplication.class, args);
    }
    
	@LoadBalanced
    @Bean
    RestTemplate restTemplte() {
		return new RestTemplate();
	}
	
    @Bean
    TomcatServletWebServerFactory servletContainer() {
		return new TomcatServletWebServerFactory() {
			// TOMCAT을 커스터마이징한다.
			@Override
			protected void customizeConnector(Connector connector) {
				super.customizeConnector(connector);
				//DELETE에는 BODY를 붙일 수 없지만, 붙일 수 있도록 변경
				connector.setParseBodyMethods("POST,PUT,DELETE");
			}
		};
	}    
}