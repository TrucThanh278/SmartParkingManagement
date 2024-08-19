/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.formatters.RoleFormatters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author trucn
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.ou.controllers",
    "com.ou.services",
    "com.ou.repositories"
})
public class WebAppContextConfigs implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new RoleFormatters());
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "djyggobeq",
                        "api_key", "446637785899928",
                        "api_secret", "7EAQ1bjCVEaO2tYArfZbYo58tXo",
                        "secure", true));
        return cloudinary;
    }

//    @Bean
//    public InternalResourceViewResolver viewResolver(){
//        InternalResourceViewResolver r = new InternalResourceViewResolver();
//        
//        r.setViewClass(JstlView.class);
//        r.setPrefix("/WEB-INF/view/pages/");
//        r.setSuffix(".jsp");
//        
//        return r;
//    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
    }

}
