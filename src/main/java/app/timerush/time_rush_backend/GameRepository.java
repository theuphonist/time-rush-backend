package app.timerush.time_rush_backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.NonNull;

public interface GameRepository extends MongoRepository<Game, String> {

    @Query("{}")
    @NonNull List<Game> findAll();

    @Query("{name:'?0'}")
    List<Game> findByName(String name);

    @Query("{joinCode:'?0'}")
    Game findByJoinCode(String joinCode);
}
