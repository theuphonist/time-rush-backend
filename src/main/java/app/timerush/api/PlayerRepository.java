package app.timerush.api;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.NonNull;

public interface PlayerRepository extends MongoRepository<Player, String> {
    @Query("{}")
    @NonNull
    List<Player> findAll();

    @Query("{name:'?0'}")
    List<Game> findByName(String name);

    @Query("{joinCode:'?0'}")
    Game findByJoinCode(String joinCode);
}
