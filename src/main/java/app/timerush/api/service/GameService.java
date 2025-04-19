package app.timerush.api.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.timerush.api.dto.GameDTO;
import app.timerush.api.model.Game;
import app.timerush.api.repository.GameRepository;
import app.timerush.api.util.GameTypes;
import app.timerush.api.util.GameUtils;

@Service
public class GameService {
    private final GameRepository gameRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public GameService(GameRepository gameRepo, ModelMapper modelMapper) {
        this.gameRepo = gameRepo;
        this.modelMapper = modelMapper;
    }

    public List<Game> getAllGamesByJoinCode(String joinCode) {
        return this.gameRepo.findAllByJoinCode(joinCode);
    }

    public Game getGameById(String id) {
        Optional<Game> optionalGame = this.gameRepo.findById(id);

        if (optionalGame.isPresent()) {
            return optionalGame.get();
        }

        return null;
    }

    public Game insertGame(GameDTO gameDto) {
        final Game game = this.modelMapper.map(gameDto, Game.class);

        final String joinCode = GameUtils.generateJoinCode(4);

        game.setJoinCode(joinCode);
        game.setStatus(GameTypes.Pending);
        game.setCreatedAt(Instant.now());

        return this.gameRepo.save(game);
    }

    public Game updateGame(String gameId, GameDTO gameDto) {
        final Optional<Game> optionalGame = this.gameRepo.findById(gameId);

        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();

            // only allow updating of certain properties e.g. we would never want to
            // update the game id; a new game should be created instead
            if (gameDto.getName() != null) {
                game.setName(gameDto.getName());
            }

            if (gameDto.getTurnLength() != null) {
                game.setTurnLength(gameDto.getTurnLength());
            }

            if (gameDto.getTurnLengthUnits() != null) {
                game.setTurnLengthUnits(gameDto.getTurnLengthUnits());
            }

            if (gameDto.getJoinCode() != null) {
                game.setJoinCode(gameDto.getJoinCode());
            }

            if (gameDto.getStatus() != null) {
                game.setStatus(gameDto.getStatus());
            }

            if (gameDto.getHostPlayerId() != null) {
                game.setHostPlayerId(gameDto.getHostPlayerId());
            }

            this.gameRepo.save(game);

            return game;
        }

        return null;
    }
}
