package app.timerush.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public List<Game> getAllGames(@RequestParam(required = false) String name) {
        if (name==null) {
            return this.gameRepo.findAll();
        }

        return this.gameRepo.findByName(name);
    }

    @GetMapping("/{joinCode}")
    public Game getGameByJoinCode(@PathVariable("joinCode") String joinCode) {
        return this.gameRepo.findByJoinCode(joinCode);
    }

    @PostMapping
    Game insertGame(@RequestBody GameDTO gameDto) {
        final Game game = this.modelMapper.map(gameDto, Game.class);

        final String joinCode = GameUtils.generateJoinCode(4);
        game.setJoinCode(joinCode);
        return this.gameRepo.save(game);
    }

    @PutMapping
    Game updateGame(@RequestBody GameDTO gameDto) {
        final Game game = this.modelMapper.map(gameDto, Game.class);

        return this.gameRepo.save(game);
    }
    
}
