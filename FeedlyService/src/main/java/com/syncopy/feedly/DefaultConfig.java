package com.syncopy.feedly;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

@Configuration
public class DefaultConfig {
	
	@Bean
	public FeedRepository feedrepository(){
		return Feign.builder().encoder(new GsonEncoder())
				.decoder(new FeedDecoder()).logger(new Logger.JavaLogger().appendToFile("C:/perf Softwares/http.log")).logLevel(Logger.Level.FULL).target(FeedRepository.class, "http://sandbox.feedly.com");
	}
	
	

}
