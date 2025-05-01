package org.furia.chatbot.infrastructure.client;

import org.furia.chatbot.config.ExternalAPIsConfig;
import org.furia.chatbot.dto.PlayersResponseDTO;
import org.furia.chatbot.dto.TeamDTO;
import org.furia.chatbot.dto.TournamentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "https://api.pandascore.co/csgo", name = "PandaScoreClient", configuration = ExternalAPIsConfig.class)
public interface PandaScoreClient {

    @GetMapping("/teams")
    List <TeamDTO> getTeams (@RequestParam("filter[name]") List <String> name);

    @GetMapping("/players")
    List <PlayersResponseDTO> getPlayers ();

    @GetMapping("/tournaments/past")
    List <TournamentResponseDTO> getPastCSTournament ();

    @GetMapping("/tournaments/running")
    List <TournamentResponseDTO> getRunningCSTournament ();

    @GetMapping("/tournaments/upcoming")
    List <TournamentResponseDTO> getUpcomingCSTournament ();

}
