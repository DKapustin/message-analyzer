package liga.medical.messageanalyzer.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.dto.MessageDto;
import liga.medical.messageanalyzer.core.api.MessageSenderService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderServiceImpl implements MessageSenderService {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public MessageSenderServiceImpl(AmqpTemplate amqpTemplate, ObjectMapper objectMapper){
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendMessage(MessageDto messageDto, String queue) throws JsonProcessingException{
       String message = objectMapper.writeValueAsString(messageDto);
       amqpTemplate.convertAndSend(queue, message);
       System.out.println("Сообщение " + message + " отправлено в очередь: " + queue );
    }
}
