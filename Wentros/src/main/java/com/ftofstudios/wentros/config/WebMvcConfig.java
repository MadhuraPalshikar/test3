package com.ftofstudios.wentros.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Spring MVC Configuration.
 * @author nachiket
 *
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurationSupport {
	
	

	/**
	 * 
	 */
	public WebMvcConfig() {
		// TODO Auto-generated constructor stub
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver view = new InternalResourceViewResolver();
		view.setPrefix("/WEB-INF/views/");
		view.setSuffix(".jsp");
		return view;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		//super.addResourceHandlers(registry);
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		
	
	}
	
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		//super.configureMessageConverters(converters);
		converters.add(converter());
       // addDefaultHttpMessageConverters(converters)
		addDefaultHttpMessageConverters(converters);
		
	}
	
	@Bean
    public MappingJacksonHttpMessageConverter converter() {
        MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
        //do your customizations here...
        ArrayList<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        converter.setSupportedMediaTypes(list);
        return converter;
    }
	
	

}
