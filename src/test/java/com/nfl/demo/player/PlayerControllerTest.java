package com.nfl.demo.player;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    void getPlayers_withTeamParam_returns200AndJson() throws Exception {
        Player p = new Player();
        p.setTeam("BUF");
        p.setPosition("QB");
        p.setFirstName("Josh");
        p.setLastName("Allen");

        when(playerService.getPlayersFromTeam("BUF"))
                .thenReturn(List.of(p));

        mockMvc.perform(get("/api/v1/player")
                        .param("team", "BUF"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"));
    }

    @Test
    void getPlayers_withTeamAndPosition_returns200() throws Exception {
        when(playerService.getPlayersByTeamAndPosition("BUF", "QB"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/player")
                        .param("team", "BUF")
                        .param("position", "QB"))
                .andExpect(status().isOk());
    }
}
