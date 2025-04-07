package app.timerush.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.timerush.api.model.Game;

public interface GameRepository extends MongoRepository<Game, String> {
    @Query("{joinCode:'?0'}")
    List<Game> findAllByJoinCode(String joinCode);
}
