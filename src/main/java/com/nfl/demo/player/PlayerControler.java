package com.nfl.demo.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/player")
public class PlayerControler {
    private final PlayerService playerService;

    @Autowired
    public PlayerControler(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player>
}
