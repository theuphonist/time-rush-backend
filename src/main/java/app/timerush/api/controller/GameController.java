package app.timerush.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.timerush.api.dto.GameDTO;
import app.timerush.api.model.Game;
import app.timerush.api.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Game getGameById(@PathVariable("id") String id) {
        return this.gameService.getGameById(id);
    }

    @CrossOrigin
    @GetMapping
    public List<Game> getAllGamesByJoinCode(@RequestParam String joinCode) {
        return this.gameService.getAllGamesByJoinCode(joinCode);
    }

    @CrossOrigin
    @PostMapping
    public Game insertGame(@RequestBody GameDTO gameDto) {
        return this.gameService.insertGame(gameDto);
    }
}
