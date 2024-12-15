package app.timerush.time_rush_backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameRepository gameRepo;

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
    
}
