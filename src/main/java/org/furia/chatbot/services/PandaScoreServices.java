package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.infrastructure.client.PandaScoreClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PandaScoreServices {

    private final PandaScoreClient pandaScoreClient;

}
