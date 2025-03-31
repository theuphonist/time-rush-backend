package app.timerush.api;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {
    enum WebSocketAction {
        UPDATE_PLAYER("updatePlayer");

        private final String stringValue;

        WebSocketAction(String stringValue) {
            this.stringValue = stringValue;
        }

        String get() {
            return this.stringValue;
        }
    }

    private final PlayerRepository playerRepo;
    private final GameRepository gameRepo;
    private final ModelMapper modelMapper;
    private final SimpMessagingTemplate template;

    @Autowired
    public PlayerController(PlayerRepository playerRepo, GameRepository gameRepo, ModelMapper modelMapper,
            SimpMessagingTemplate template) {
        this.playerRepo = playerRepo;
        this.gameRepo = gameRepo;
        this.modelMapper = modelMapper;
        this.template = template;
    }

    @CrossOrigin
    @GetMapping
    public List<Player> getAllPlayersByGameId(@RequestParam String gameId) {
        return this.playerRepo.findAllByGameId(gameId);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable("id") String id) {
        final Optional<Player> optionalPlayer = this.playerRepo.findById(id);

        if (optionalPlayer.isPresent()) {
            return optionalPlayer.get();
        }

        return null;
    }

    @CrossOrigin
    @PostMapping
    Player insertPlayer(@RequestBody PlayerDTO playerDTO) {
        final Player player = this.modelMapper.map(playerDTO, Player.class);

        player.setCreatedAt(Instant.now());
        player.setIsConnected(true);

        final List<Player> otherPlayers = this.playerRepo.findAllByGameId(player.getGameId());

        final Optional<Game> optionalGame = this.gameRepo.findById(player.getGameId());

        if (optionalGame.isEmpty()) {
            return null;
        }

        final Player newPlayer = this.playerRepo.save(player);

        final Game game = optionalGame.get();

        // make this player the host if there isn't one already
        if (game.getHostPlayerId() == null) {
            game.setHostPlayerId(newPlayer.getId());
            this.gameRepo.save(game);
        }

        MessageUtils.sendUpdatePlayerMessage(player.getGameId(), this.template);

        return newPlayer;
    }

    @CrossOrigin
    @PutMapping
    Player updatePlayer(@RequestBody PlayerDTO playerDTO) {
        final Optional<Player> optionalPlayer = this.playerRepo.findById(playerDTO.getId());

        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();

            if (playerDTO.getId() != null) {
                player.setId(playerDTO.getId());
            }

            if (playerDTO.getName() != null) {
                player.setName(playerDTO.getName());
            }

            if (playerDTO.getColor() != null) {
                player.setColor(playerDTO.getColor());
            }

            if (playerDTO.getGameId() != null) {
                player.setGameId(playerDTO.getGameId());
            }

            if (playerDTO.getPosition() != null) {
                player.setPosition(playerDTO.getPosition());
            }

            if (playerDTO.getIsConnected() != null) {
                player.setIsConnected(playerDTO.getIsConnected());
            }

            if (playerDTO.getCreatedAt() != null) {
                player.setCreatedAt(playerDTO.getCreatedAt());
            }

            this.playerRepo.save(player);

            MessageUtils.sendUpdatePlayerMessage(player.getGameId(), this.template);

            return player;
        }

        return null;
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    Player deletePlayer(@PathVariable("id") String playerId) {
        Optional<Player> optionalPlayer = this.playerRepo.findById(playerId);

        if (optionalPlayer.isEmpty()) {
            return null;
        }

        Player player = optionalPlayer.get();

        this.playerRepo.deleteById(playerId);

        Optional<Game> optionalGame = this.gameRepo.findById(player.getGameId());

        if (optionalGame.isEmpty()) {
            return player;
        }

        Game game = optionalGame.get();

        if (!game.getHostPlayerId().equals(player.getId())) {
            return player;
        }

        final List<Player> remainingPlayers = this.playerRepo.findAllByGameId(player.getGameId());

        if (remainingPlayers.isEmpty()) {
            game.setHostPlayerId(null);
            this.gameRepo.save(game);
            return player;
        }

        for (Player remainingPlayer : remainingPlayers) {
            if (!remainingPlayer.getId().equals(playerId)) {
                game.setHostPlayerId(remainingPlayer.getId());
                this.gameRepo.save(game);
                break;
            }
        }

        MessageUtils.sendUpdatePlayerMessage(player.getGameId(), this.template);

        return player;
    }

}
