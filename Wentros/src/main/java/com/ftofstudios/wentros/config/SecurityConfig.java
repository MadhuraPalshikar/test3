package com.ftofstudios.wentros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This is security configartion class.
 * 
 * @author nachiket
 *
 */
@Configuration
@ImportResource("classpath:security.xml")
public class SecurityConfig {

	/**
	 * 
	 */
	public SecurityConfig() {
		// TODO Auto-generated constructor stub
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
    	return NoOpPasswordEncoder.getInstance();
	}
    
	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}

}
