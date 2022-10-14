package liga.medical.messageanalyzer.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.dto.MessageDto;

public interface MessageSenderService {
    void sendMessage(MessageDto messageDto, String queue) throws JsonProcessingException;
}

