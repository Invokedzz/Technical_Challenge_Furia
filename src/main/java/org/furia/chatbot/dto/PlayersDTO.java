package org.furia.chatbot.dto;

public record PlayersDTO (

        TeamDTO current_team,

        Boolean active,

        Integer age,

        String birthday,

        String first_name,

        String last_name,

        String name,

        String nationality

) {}
