package com.nfl.demo.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@RestController
@RequestMapping(path = {"api/v1/player"})
public class PlayerControler {
    private final PlayerService playerService;

    @Autowired
    public PlayerControler(PlayerService playerService) {
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

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        Player createdPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player){
        Player resultPlayer = playerService.updatePlayer(player);
        if (resultPlayer != null) {
            return new ResponseEntity<>(resultPlayer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{playerName}")
    public ResponseEntity<String> deletePlayer (@PathVariable String playerName){
        playerService.deletePlayer(playerName);
        return new  ResponseEntity<>("Player deleted succesfully", HttpStatus.OK);
    }

}
