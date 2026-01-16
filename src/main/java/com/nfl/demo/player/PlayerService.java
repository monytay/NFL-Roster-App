package com.nfl.demo.player;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }

    public List<Player> getPlayersFromTeam(String team){
        String t = team.toLowerCase();
        return playerRepository.findAll().stream()
                .filter(player -> player.getTeam() != null &&
                        player.getTeam().toLowerCase().contains(t))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByName(String searchText){
        String q = searchText.toLowerCase();
        return playerRepository.findAll().stream()
                .filter(player -> player.getFirstName() != null &&
                        player.getFirstName().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByPosition(String searchText){
        String q = searchText.toLowerCase();
        return playerRepository.findAll().stream()
                .filter(player -> player.getPosition() != null &&
                        player.getPosition().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByLastName(String searchText){
        String q = searchText.toLowerCase();
        return playerRepository.findAll().stream()
                .filter(player -> player.getLastName() != null &&
                        player.getLastName().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByTeamAndPosition(String team, String position){
        return playerRepository.findAll().stream()
                .filter(player -> team.equals(player.getTeam()) && position.equals(player.getPosition()))
                .collect(Collectors.toList());
    }

    public Player addPlayer(Player player){
        return playerRepository.save(player);
    }

    public Player updatePlayer(Player updatedPlayer) {
        Optional<Player> existingPlayer =
                playerRepository.findByFirstName(updatedPlayer.getFirstName());

        if (existingPlayer.isPresent()){
            Player playerToUpdate = existingPlayer.get();
            playerToUpdate.setFirstName(updatedPlayer.getFirstName());
            playerToUpdate.setLastName(updatedPlayer.getLastName());
            playerToUpdate.setTeam(updatedPlayer.getTeam());
            playerToUpdate.setPosition(updatedPlayer.getPosition());

            return playerRepository.save(playerToUpdate);
        }
        return null;
    }

    @Transactional
    public void deletePlayer(String playerName){
        playerRepository.deleteByFirstName(playerName);
    }
}
