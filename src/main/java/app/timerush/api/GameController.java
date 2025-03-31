package app.timerush.api;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameRepository gameRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public GameController(GameRepository gameRepo, ModelMapper modelMapper) {
        this.gameRepo = gameRepo;
        this.modelMapper = modelMapper;
    }

    @CrossOrigin
    @GetMapping
    public List<Game> getAllGamesByJoinCode(@RequestParam String joinCode) {
        return this.gameRepo.findAllByJoinCode(joinCode);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Game getGameById(@PathVariable("id") String id) {
        Optional<Game> optionalGame = this.gameRepo.findById(id);

        if (optionalGame.isPresent()) {
            return optionalGame.get();
        }

        return null;
    }

    @CrossOrigin
    @PostMapping
    Game insertGame(@RequestBody GameDTO gameDto) {
        final Game game = this.modelMapper.map(gameDto, Game.class);

        final String joinCode = GameUtils.generateJoinCode(4);

        game.setJoinCode(joinCode);
        game.setStatus(GameTypes.Pending);
        game.setCreatedAt(Instant.now());

        return this.gameRepo.save(game);
    }

}
