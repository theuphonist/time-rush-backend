package app.timerush.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import app.timerush.api.dto.PlayerDTO;
import app.timerush.api.model.Player;
import app.timerush.api.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable("id") String id) {
        return this.playerService.getPlayerById(id);
    }

    @CrossOrigin
    @GetMapping
    public List<Player> getAllPlayersByGameId(@RequestParam String gameId) {
        return this.playerService.getAllPlayersByGameId(gameId);
    }

    @CrossOrigin
    @PostMapping
    Player insertPlayer(@RequestBody PlayerDTO playerDto) {
        return this.playerService.insertPlayer(playerDto);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    Player updatePlayer(@PathVariable("id") String playerId, @RequestBody PlayerDTO playerDto) {
        playerDto.setId(playerId);
        return this.playerService.updatePlayer(playerDto);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    Player deletePlayer(@PathVariable("id") String playerId) {
        return this.playerService.deletePlayer(playerId);
    }

}
