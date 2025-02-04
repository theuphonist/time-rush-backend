package app.timerush.api;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WebSocketSessionRepository extends MongoRepository<WebSocketSession, String> {
}
