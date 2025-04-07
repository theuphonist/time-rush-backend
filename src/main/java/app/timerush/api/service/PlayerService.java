package app.timerush.api.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.timerush.api.dto.PlayerDTO;
import app.timerush.api.model.Game;
import app.timerush.api.model.Player;
import app.timerush.api.repository.GameRepository;
import app.timerush.api.repository.PlayerRepository;

@Service
public class PlayerService {
    private final PlayerRepository playerRepo;
    private final GameRepository gameRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerService(PlayerRepository playerRepo, GameRepository gameRepo, ModelMapper modelMapper) {
        this.playerRepo = playerRepo;
        this.gameRepo = gameRepo;
        this.modelMapper = modelMapper;
    }

    public List<Player> getAllPlayersByGameId(String gameId) {
        return this.playerRepo.findAllByGameId(gameId);
    }

    public Player getPlayerById(String id) {
        final Optional<Player> optionalPlayer = this.playerRepo.findById(id);

        if (optionalPlayer.isPresent()) {
            return optionalPlayer.get();
        }

        return null;
    }

    public Player insertPlayer(PlayerDTO playerDTO) {
        final Player player = this.modelMapper.map(playerDTO, Player.class);

        player.setCreatedAt(Instant.now());
        player.setSessionId(null);

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

        return newPlayer;
    }

    public Player updatePlayer(PlayerDTO playerDTO) {
        final Optional<Player> optionalPlayer = this.playerRepo.findById(playerDTO.getId());

        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();

            // only allow updating of certain properties e.g. we would never want to
            // update the player id a new player should be created instead
            if (playerDTO.getName() != null) {
                player.setName(playerDTO.getName());
            }

            if (playerDTO.getColor() != null) {
                player.setColor(playerDTO.getColor());
            }

            if (playerDTO.getPosition() != null) {
                player.setPosition(playerDTO.getPosition());
            }

            if (playerDTO.getSessionId() != null) {
                player.setSessionId(playerDTO.getSessionId());
            }

            this.playerRepo.save(player);

            return player;
        }

        return null;
    }

    public Player deletePlayer(String playerId) {
        Optional<Player> optionalPlayer = this.playerRepo.findById(playerId);

        if (optionalPlayer.isEmpty()) {
            return null;
        }

        this.playerRepo.deleteById(playerId);

        Player player = optionalPlayer.get();

        Optional<Game> optionalGame = this.gameRepo.findById(player.getGameId());

        if (optionalGame.isEmpty()) {
            return player;
        }

        // if this player was the host of their game, find a new host
        // if no players are left, set the host to null
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

        return player;
    }

    public Player connectPlayer(String playerId, String sessionId) {
        final PlayerDTO playerDto = new PlayerDTO();

        playerDto.setId(playerId);
        playerDto.setSessionId(sessionId);

        return this.updatePlayer(playerDto);
    }

    public Player disconnectPlayerBySessionId(String sessionId) {
        final Optional<Player> optionalPlayer = this.playerRepo.findBySessionId(sessionId);

        if (optionalPlayer.isEmpty()) {
            return null;
        }

        final Player player = optionalPlayer.get();

        player.setSessionId(null);

        return this.playerRepo.save(player);
    }
}
