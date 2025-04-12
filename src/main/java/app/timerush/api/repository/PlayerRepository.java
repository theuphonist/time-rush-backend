package app.timerush.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.NonNull;

import app.timerush.api.model.Player;

public interface PlayerRepository extends MongoRepository<Player, String> {
    @Query("{}")
    @NonNull
    List<Player> findAll();

    @Query("{gameId:'?0'}")
    List<Player> findAllByGameId(String gameId);

    @Query("{gameId:'?0', connected: null}")
    List<Player> findAllDisconnectedByGameId(String gameId);

    @Query("{sessionId:'?0'}")
    Optional<Player> findBySessionId(String sessionId);
}
