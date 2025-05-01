package org.furia.chatbot.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.furia.chatbot.infrastructure.client.PandaScoreClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden @RestController
@RequestMapping("/external")
public record PandaScoreController (PandaScoreClient pandaScoreClient) {

    @GetMapping("/teams")
    public ResponseEntity <Void> getTeams () {

        return ResponseEntity.status(HttpStatus.OK).build();

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
