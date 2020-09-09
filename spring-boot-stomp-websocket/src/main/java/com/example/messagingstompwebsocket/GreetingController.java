package com.example.messagingstompwebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

	private int DELAY = 1000;

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(Message message) throws Exception {
		Thread.sleep(DELAY); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}

}
