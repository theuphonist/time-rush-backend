package app.timerush.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface GameRepository extends MongoRepository<Game, String> {
    @Query("{joinCode:'?0'}")
    Game findByJoinCode(String joinCode);
}
