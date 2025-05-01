package org.furia.chatbot.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.furia.chatbot.dto.TeamDTO;
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
    public ResponseEntity <Void> getPlayers () {

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/tournament/past")
    public ResponseEntity <Void> getPastCSTournaments () {

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/tournament/running")
    public ResponseEntity <Void> getRunningCSTournament () {

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/tournament/upcoming")
    public ResponseEntity <Void> getUpcomingCSTournament () {

        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
