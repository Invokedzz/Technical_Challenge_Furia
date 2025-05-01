package org.furia.chatbot.services;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.furia.chatbot.dto.PlayersDTO;
import org.furia.chatbot.dto.TeamDTO;
import org.furia.chatbot.dto.TournamentDTO;
import org.furia.chatbot.exceptions.BadRequestException;
import org.furia.chatbot.exceptions.NotFoundException;
import org.furia.chatbot.exceptions.UnauthorizedException;
import org.furia.chatbot.exceptions.UnprocessableEntity;
import org.furia.chatbot.infrastructure.client.PandaScoreClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PandaScoreServices {

    private final PandaScoreClient pandaScoreClient;

    public List <TeamDTO> getTeams (List <String> name) {

        try {

            return pandaScoreClient.getTeams(name);

        } catch (FeignException.BadRequest ex) {

            throw new BadRequestException(ex.getMessage());

        } catch (FeignException.NotFound ex) {

            throw new NotFoundException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedException(ex.getMessage());

        } catch (FeignException.UnprocessableEntity ex) {

            throw new UnprocessableEntity(ex.getMessage());

        }

    }

    public List <PlayersDTO> getPlayers (List <Integer> team_id) {

        try {

            return pandaScoreClient.getPlayers(team_id);

        } catch (FeignException.BadRequest ex) {

            throw new BadRequestException(ex.getMessage());

        } catch (FeignException.NotFound ex) {

            throw new NotFoundException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedException(ex.getMessage());

        } catch (FeignException.UnprocessableEntity ex) {

            throw new UnprocessableEntity(ex.getMessage());

        }

    }

    public List <TournamentDTO> getTournaments (String team) {

        try {

            var tournaments = pandaScoreClient.getTournaments();

            for (TournamentDTO tournament : tournaments) {

                for (TeamDTO t : tournament.teams()) {

                    if (t.name().equals(team)) {

                        return List.of(tournament);

                    }

                }

            }

        } catch (FeignException.BadRequest ex) {

            throw new BadRequestException(ex.getMessage());

        } catch (FeignException.NotFound ex) {

            throw new NotFoundException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedException(ex.getMessage());

        } catch (FeignException.UnprocessableEntity ex) {

            throw new UnprocessableEntity(ex.getMessage());

        }

        return List.of();

    }

}
