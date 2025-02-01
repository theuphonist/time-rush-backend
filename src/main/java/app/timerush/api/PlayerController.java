package app.timerush.api;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping
    public List<Player> getAllPlayersByGameId(@RequestParam String gameId) {
        return this.playerRepo.findAllByGameId(gameId);
    }

    @CrossOrigin
    @PostMapping
    Player insertPlayer(@RequestBody PlayerDTO playerDTO) {
        final Player player = this.modelMapper.map(playerDTO, Player.class);
        return this.playerRepo.save(player);
    }

    @CrossOrigin
    @PutMapping
    Player updatePlayer(@RequestBody PlayerDTO playerDTO) {
        final Optional<Player> optionalPlayer = this.playerRepo.findById(playerDTO.getId());

        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();

            if (playerDTO.getId() != null) {
                player.setId(playerDTO.getId());
            }

            if (playerDTO.getName() != null) {
                player.setName(playerDTO.getName());
            }

            if (playerDTO.getColor() != null) {
                player.setColor(playerDTO.getColor());
            }

            if (playerDTO.getIsHost()) {
                player.setIsHost(playerDTO.getIsHost());
            }

            if (playerDTO.getGameId() != null) {
                player.setGameId(playerDTO.getGameId());
            }

            if (playerDTO.getPosition() != null) {
                player.setPosition(playerDTO.getPosition());
            }

            this.playerRepo.save(player);
            return player;
        }

        return null;
    }

    @CrossOrigin
    @DeleteMapping
    Player deletePlayer(@RequestParam String playerId) {
        Optional<Player> optionalPlayer = this.playerRepo.findById(playerId);

        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            this.playerRepo.deleteById(playerId);
            return player;
        }

        return null;
    }
}
