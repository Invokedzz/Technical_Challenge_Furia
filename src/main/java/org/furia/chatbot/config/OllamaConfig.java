package org.furia.chatbot.config;

import org.furia.chatbot.dto.PlayersDTO;
import org.furia.chatbot.dto.RoasterDTO;
import org.furia.chatbot.dto.TeamDTO;
import org.furia.chatbot.dto.TournamentDTO;
import org.furia.chatbot.services.PandaScoreServices;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
public class OllamaConfig {

    private final ChatClient chatClient;

    private final PandaScoreServices pandaScoreServices;

    public OllamaConfig (ChatClient.Builder chatClient, PandaScoreServices pandaScoreServices) {

        this.chatClient = chatClient.build();

        this.pandaScoreServices = pandaScoreServices;

    }

    public String getAIResponse (String message) {

        String context = buildFuriaContext();

        return Objects.requireNonNull(chatClient.prompt().user(message).system("Você é uma IA especialista em esports. A FURIA é uma organização de esports de destaque em jogos como CS:GO. Com base nas informações fornecidas abaixo, gere uma análise sobre o time, jogadores e torneios da FURIA.\n" + context).call().content()).replace("/n", "");

    }

    private List <PlayersDTO> getPlayersFromFURIA () {

        List <Integer> listId = new ArrayList<>();

        listId.add(124530);

        return pandaScoreServices.getPlayers(listId);

    }

    private List <TeamDTO> getFURIATeam () {

        List <String> list = setupListOfStrings();

        return pandaScoreServices.getTeams(list);

    }

    private List <TournamentDTO> getFURIATournaments () {

        List <String> list = setupListOfStrings();

        return pandaScoreServices.getTournaments(list.getFirst());

    }

    private List <String> setupListOfStrings () {

        List <String> list = new ArrayList<>();

        list.add("FURIA");

        return list;

    }

    private String buildFuriaContext() {

        var team = getFURIATeam();
        var players = getPlayersFromFURIA();
        var tournaments = getFURIATournaments();

        StringBuilder context = new StringBuilder();

        context.append("Dados da Organização FURIA\n\n");

        // Equipe
        if (!team.isEmpty()) {

            TeamDTO t = team.getFirst();

            context.append("Equipe:\n");

            context.append(String.format(
                    "Nome: %s | Acrônimo: %s | Localização: %s | ID: %d\n\n",
                    t.name(), t.acronym(), t.location(), t.id()
            ));

        }

        if (!players.isEmpty()) {

            context.append("Jogadores Atuais:\n");

            for (PlayersDTO p : players) {

                context.append(String.format(
                        "- Nome: %s %s (%s), Nacionalidade: %s, Idade: %d, Ativo: %s\n",
                        p.first_name(), p.last_name(), p.name(),
                        p.nationality(), p.age(), p.active() ? "Sim" : "Não"
                ));
            }

            context.append("\n");
        }

        if (!tournaments.isEmpty()) {
            context.append("Torneios Recentes:\n");

            for (TournamentDTO t : tournaments) {

                context.append(String.format("🏆 Torneio (Tier %s):\n", t.tier()));

                context.append(String.format("- País: %s\n", t.country()));

                context.append(String.format("- Início: %s | Fim: %s\n", t.begin_at(), t.end_at()));

                if (t.teams() != null && !t.teams().isEmpty()) {

                    context.append("- Times Participantes:\n");

                    for (TeamDTO teamDTO : t.teams()) {
                        context.append(String.format("  • %s (%s)\n", teamDTO.name(), teamDTO.acronym()));
                    }

                }

                if (t.expected_roster() != null && !t.expected_roster().isEmpty()) {

                    context.append("- Elenco Esperado:\n");

                    for (RoasterDTO roster : t.expected_roster()) {

                        for (PlayersDTO p : roster.players()) {

                            context.append(String.format(
                                    "  - Nome: %s %s (%s), Nacionalidade: %s, Idade: %d, Ativo: %s\n",
                                    p.first_name(), p.last_name(), p.name(),
                                    p.nationality(), p.age(), p.active() ? "Sim" : "Não"

                            ));

                        }

                    }

                }

                context.append("\n");
            }
        }

        return context.toString();

    }

}
