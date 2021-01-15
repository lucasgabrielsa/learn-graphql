package com.lucasgabriel.learngraphql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class LearnGraphqlApplication {

	private static final String ACCESS_URLS_MESSAGE_LOG =
			"\n\n Access URLs:\n----------------------------------------------------------\n\t Playground: \t{} \n\t Voyager: \t{} \n----------------------------------------------------------\n";

	public static void main(String[] args) {
		try {
			System.setProperty("spring.devtools.restart.enabled", "false");
			final SpringApplication app = new SpringApplication(LearnGraphqlApplication.class);
			final Environment env = app.run().getEnvironment();

			log.info(ACCESS_URLS_MESSAGE_LOG, getAccessUrl(env, "playground"), getAccessUrl(env, "voyager"));

		} catch (Exception e) {
			log.error("Start Error.", e);
		}
	}

	private static String getAccessUrl(Environment env, String applicationContext) throws UnknownHostException {

		if (env.getActiveProfiles()[0].equalsIgnoreCase("local")) {
			return String.format(
					"http://%s:%s/%s",
					InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"), applicationContext);
		}

		return String.format("https://aplication-name.%s.lucasgabriel.dev", env.getActiveProfiles()[0]);
	}

}
