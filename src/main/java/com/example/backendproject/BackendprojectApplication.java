package com.example.backendproject;

import com.example.backendproject.service.UserServiceImpl;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.example.backendproject.service.files.FileStorageService;


import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootApplication
public class BackendprojectApplication {
    @Autowired
    UserServiceImpl userService;

    @Resource
    FileStorageService fileStorageService;
    public static void main(String[] args) {

        SpringApplication.run(BackendprojectApplication.class, args);
    }
    @Bean
    BCryptPasswordEncoder getBCE() {
        return new BCryptPasswordEncoder();
    }



  @Bean
  public CorsFilter corsFilter() {
      CorsConfiguration corsConfiguration = new CorsConfiguration();
      corsConfiguration.setAllowCredentials(true);
      corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
      corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
              "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
              "Access-Control-Request-Method", "Access-Control-Request-Headers"));
      corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
              "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
      corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
      UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
      urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
      return new CorsFilter(urlBasedCorsConfigurationSource);
  }

    public void run(String... arg) throws Exception {
        fileStorageService.init();

    }




}
record GreetingREquest (String name ){} ;
record GreetingResponse(String message){} ;
@Controller
@CrossOrigin(origins = "*")
class WebSocketController{
    @MessageMapping("/chat")
    @SendTo("topic/greetingd")

    GreetingResponse greet(GreetingREquest GR) throws  Exception{
        Assert.isTrue(Character.isUpperCase(GR.name().charAt(0)) );
        Thread.sleep(1000);
        return new GreetingResponse("Hello" +
                GR.name()+"!");
    }
}

@Configuration
@EnableWebSocketMessageBroker
class GreetingWebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

registry.enableSimpleBroker("/topic");
registry.setApplicationDestinationPrefixes("/app") ;


    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
    }
}