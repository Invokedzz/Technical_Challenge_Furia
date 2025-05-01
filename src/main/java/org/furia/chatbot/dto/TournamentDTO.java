package org.furia.chatbot.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TournamentDTO (

        LocalDateTime begin_at,

        String country,

        LocalDateTime end_at,

        List <RoasterDTO> expected_roster,

        List <TeamDTO> teams,

        String tier

) {}
