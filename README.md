### To run locally:

`./mvnw spring-boot:run -Dspring-boot.run.profiles=dev`

### To run on production server (requires keystore):

`java -jar -Dspring.profiles.active=prod <jar-file-name>.jar`

### STOMP Messaging

WebSocket connections are initiated via the `/messaging` endpoint.

Messages sent from client to server are prefixed with `/timerush`.

Ex: The client sends a message to `/timerush/connect` to notify the server of the connecting player's id.

Messages sent from server to client will be prefixed with `/topic`.

Ex: The server sends a message to `/topic/ABCD` to notify all subscribers of an event in game id `ABCD`.
