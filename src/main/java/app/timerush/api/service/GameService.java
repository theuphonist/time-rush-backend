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
}
