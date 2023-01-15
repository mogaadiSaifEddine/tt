package com.example.backendproject.SecurityConfig;

import com.example.backendproject.entities.Block;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Objects;

@EnableWebMvc
@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {




    @Value("${ConceptuelCardFolder}")
    private String conceptuelCardFolder;
    @Value("${CoureseFolder}")
    private String coureseFolder;
    @Value("${BlockFilesFolder}")
    private String BlockFilesFolder;

    @Value("${clubFolder}")
    private String clubFolder;
    @Value("${BlockFilesFolderToGetFrom}")
    private String BlockFilesFolderToGetFrom;

    @Value("${ArticleClubFolder}")
    private String articleClubFolder;
    @Value("${ExerciceFolder}")
    private String ExerciceFolder;
    @Value("${CoursePR}")
    private String CoursePRFolder;
    @Value("${host}")
    private String host;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    System.out.println(host);

        if (Objects.equals(host, "PROD") || Objects.equals(host, "TEST")){
            System.out.println("HOST " + BlockFilesFolder);
            registry.addResourceHandler("/filgetter/block/**").addResourceLocations("file:/../"+BlockFilesFolder+"/");
            registry.addResourceHandler("/filgetter/exercice/**").addResourceLocations("file:/../../"+ExerciceFolder+"/");

        }
        else {
            System.out.println("HOST DEV" + BlockFilesFolder);
            registry.addResourceHandler("/filgetter/block/**").addResourceLocations("classpath:/" + BlockFilesFolderToGetFrom + "/");
            registry.addResourceHandler("/filgetter/exercice/**").addResourceLocations("classpath:/static/uploads/" + ExerciceFolder + "/");
        }
//        registry.addResourceHandler("/filgetter/exercice/").addResourceLocations("classpath:/static/uploads/");
//        registry.addResourceHandler("/filgetter/**").addResourceLocations("file:/../"+BlockFilesFolder+"/");
    }


}
