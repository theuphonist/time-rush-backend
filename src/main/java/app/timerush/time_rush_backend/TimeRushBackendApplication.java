package app.timerush.time_rush_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TimeRushBackendApplication {

	@Autowired
	GameRepository gameRepo;

	public static void main(String[] args) {
		SpringApplication.run(TimeRushBackendApplication.class, args);
	}

}
