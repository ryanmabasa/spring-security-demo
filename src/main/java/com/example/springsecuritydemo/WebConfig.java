package com.example.springsecuritydemo;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {


    /*
    * Now you need to provide a controller that handles requests at that path.
    * Because your login page will be fairly simple—nothing but a view—it’s easy enough to declare it as a view controller in WebConfig.
    * The following addViewControllers() method sets up the login page view controller alongside the view controller that maps “/” to the home controller:
    * */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }
}
