package liga.medical.messageanalyzer.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.messageanalyzer.core.api.MessageSenderService;
import liga.medical.messageanalyzer.core.config.RabbitConfig;
import org.springframework.http.ResponseEntity;
import liga.medical.dto.MessageDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageSenderService messageSenderService;

    public MessageController(MessageSenderService messageSenderService) {
        this.messageSenderService = messageSenderService;
    }

    @PostMapping
    ResponseEntity<String> sendMessage(@RequestBody MessageDto messageDto) throws JsonProcessingException {
        messageSenderService.sendMessage(messageDto, RabbitConfig.QUEUE);
        return ResponseEntity.ok(messageDto.toString());
    }
}
