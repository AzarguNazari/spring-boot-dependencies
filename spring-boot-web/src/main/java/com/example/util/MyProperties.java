package com.example.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class MyProperties {
	
	@Value("${title}")
	private String title;

	@Value("${description}")
	private String description;
}
