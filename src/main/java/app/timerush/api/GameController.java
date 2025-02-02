package app.timerush.api;

import java.time.Instant;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("")
    public Game getGameByJoinCode(@RequestParam String joinCode) {
        return this.gameRepo.findByJoinCode(joinCode);
    }

    @CrossOrigin
    @PostMapping
    Game insertGame(@RequestBody GameDTO gameDto) {
        final Game game = this.modelMapper.map(gameDto, Game.class);

        final String joinCode = GameUtils.generateJoinCode(4);
        game.setJoinCode(joinCode);

        game.setCreatedAt(Instant.now());

        return this.gameRepo.save(game);
    }

}
