package com.nfl.demo.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = {"api/v1/player"})
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getPlayers(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String lastName) {
        if (team != null && position != null) {
            return playerService.getPlayersByTeamAndPosition(team, position);
        } else if (team != null ) {
            return playerService.getPlayersFromTeam(team);
        } else if (firstName != null && lastName != null) {
            return playerService.getPlayerByFirstAndLastName(firstName, lastName);
        } else if (firstName != null) {
            return playerService.getPlayersByName(firstName);
        } else if (lastName != null) {
            return playerService.getPlayersByLastName(lastName);
        } else if (position != null){
            return playerService.getPlayersByPosition(position);
        }else {
            return  playerService.getPlayers();
        }
    }

}
