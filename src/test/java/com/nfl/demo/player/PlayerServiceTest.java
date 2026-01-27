package com.nfl.demo.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerServiceTest {

    private PlayerRepository playerRepository;
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        playerRepository = mock(PlayerRepository.class);
        playerService = new PlayerService(playerRepository);
    }

    // Helper to create players quickly
    private Player player(String team, String pos, String first, String last) {
        Player p = new Player();
        p.setTeam(team);
        p.setPosition(pos);
        p.setFirstName(first);
        p.setLastName(last);
        return p;
    }

    @Test
    void getPlayers_returnsAllPlayersFromRepo() {
        when(playerRepository.findAll()).thenReturn(List.of(
                player("BUF", "QB", "Josh", "Allen"),
                player("KC", "QB", "Patrick", "Mahomes")
        ));

        List<Player> result = playerService.getPlayers();

        assertThat(result).hasSize(2);
        verify(playerRepository).findAll();
    }

    @Test
    void getPlayersFromTeam_filtersCaseInsensitive_contains() {
        when(playerRepository.findAll()).thenReturn(List.of(
                player("BUF", "QB", "Josh", "Allen"),
                player("buffalo", "WR", "Stefon", "Diggs"),
                player("KC", "QB", "Patrick", "Mahomes"),
                player(null, "QB", "No", "Team") // should not crash
        ));

        List<Player> result = playerService.getPlayersFromTeam("BuF");

        assertThat(result).extracting(Player::getTeam)
                .containsExactlyInAnyOrder("BUF", "buffalo");
    }

    @Test
    void getPlayersByName_filtersFirstName_caseInsensitive_contains() {
        when(playerRepository.findAll()).thenReturn(List.of(
                player("BUF", "QB", "Josh", "Allen"),
                player("KC", "QB", "Patrick", "Mahomes"),
                player("PHI", "WR", "AJ", "Brown"),
                player("NYJ", "QB", null, "Rogers") // should not crash
        ));

        List<Player> result = playerService.getPlayersByName("jo");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFirstName()).isEqualTo("Josh");
    }

    @Test
    void getPlayerByFirstAndLastName_matchesExact_caseInsensitive_equals() {
        when(playerRepository.findAll()).thenReturn(List.of(
                player("BUF", "QB", "Josh", "Allen"),
                player("BUF", "QB", "JOSH", "ALLEN"), // both should match because equals after lowercasing
                player("KC", "QB", "Patrick", "Mahomes"),
                player("NYJ", "QB", "Josh", "Rosen")
        ));

        List<Player> result = playerService.getPlayerByFirstAndLastName("josh", "allen");

        assertThat(result).hasSize(2);
        assertThat(result).allMatch(p -> p.getLastName().equalsIgnoreCase("allen"));
    }

    @Test
    void getPlayersByPosition_filtersCaseInsensitive_contains() {
        when(playerRepository.findAll()).thenReturn(List.of(
                player("BUF", "QB", "Josh", "Allen"),
                player("KC", "QB", "Patrick", "Mahomes"),
                player("BUF", "WR", "Stefon", "Diggs"),
                player("BUF", null, "No", "Position") // should not crash
        ));

        List<Player> result = playerService.getPlayersByPosition("qb");

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Player::getPosition)
                .allMatch(pos -> pos.equalsIgnoreCase("QB"));
    }

    @Test
    void getPlayersByLastName_filtersCaseInsensitive_contains() {
        when(playerRepository.findAll()).thenReturn(List.of(
                player("BUF", "QB", "Josh", "Allen"),
                player("KC", "QB", "Patrick", "Mahomes"),
                player("PHI", "WR", "AJ", "Brown")
        ));

        List<Player> result = playerService.getPlayersByLastName("all");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getLastName()).isEqualTo("Allen");
    }

    @Test
    void getPlayersByTeamAndPosition_matchesExact_equals() {
        when(playerRepository.findAll()).thenReturn(List.of(
                player("BUF", "QB", "Josh", "Allen"),
                player("BUF", "WR", "Stefon", "Diggs"),
                player("KC", "QB", "Patrick", "Mahomes")
        ));

        List<Player> result = playerService.getPlayersByTeamAndPosition("BUF", "QB");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFirstName()).isEqualTo("Josh");
    }
}
