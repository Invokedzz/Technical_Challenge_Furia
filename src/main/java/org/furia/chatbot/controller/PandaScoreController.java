package org.furia.chatbot.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.furia.chatbot.dto.PlayersDTO;
import org.furia.chatbot.dto.TeamDTO;
import org.furia.chatbot.dto.TournamentDTO;
import org.furia.chatbot.services.PandaScoreServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Hidden @RestController
@RequestMapping("/external")
public record PandaScoreController (PandaScoreServices pandaScoreServices) {

    @GetMapping("/teams")
    public ResponseEntity <List<TeamDTO>> getTeams (@RequestParam("name") List <String> name) {

        var response = pandaScoreServices.getTeams(name);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/players")
    public ResponseEntity <List<PlayersDTO>> getPlayers (@RequestParam("teamId") List <Integer> teamId) {

        var response = pandaScoreServices.getPlayers(teamId);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/tournaments")
    public ResponseEntity <List<TournamentDTO>> getTournaments (@RequestParam("team") String team) {

        var response = pandaScoreServices.getTournaments(team);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
