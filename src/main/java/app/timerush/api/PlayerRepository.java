package app.timerush.api;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.NonNull;

public interface PlayerRepository extends MongoRepository<Player, String> {
    @Query("{}")
    @NonNull
    List<Player> findAll();

    @Query("{gameId:'?0'}")
    List<Player> findAllByGameId(String gameId);
}
