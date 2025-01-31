package app.timerush.api;

import java.util.List;

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
@RequestMapping("/player")
public class PlayerController {
    private final PlayerRepository playerRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerController(PlayerRepository playerRepo, ModelMapper modelMapper) {
        this.playerRepo = playerRepo;
        this.modelMapper = modelMapper;
    }

    @CrossOrigin
    @GetMapping("")
    public List<Player> getAllGames(@RequestParam String gameId) {
        return this.playerRepo.findAllByGameId(gameId);
    }

    @CrossOrigin
    @PostMapping
    Player insertPlayer(@RequestBody PlayerDTO playerDTO) {
        final Player player = this.modelMapper.map(playerDTO, Player.class);

        return this.playerRepo.save(player);
    }
}
