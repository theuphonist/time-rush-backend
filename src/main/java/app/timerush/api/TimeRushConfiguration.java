package app.timerush.api;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import app.timerush.api.repository.AfterGameSaveListener;
import app.timerush.api.repository.AfterPlayerSaveListener;
import app.timerush.api.service.MessageService;
import app.timerush.api.service.PlayerService;

@Configuration
@EnableWebSocketMessageBroker
public class TimeRushConfiguration implements WebSocketMessageBrokerConfigurer {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public AfterPlayerSaveListener afterPlayerSaveListener(MessageService messageService, PlayerService playerService) {
        return new AfterPlayerSaveListener(messageService, playerService);
    }

    @Bean
    public AfterGameSaveListener afterGameSaveListener(MessageService messageService) {
        return new AfterGameSaveListener(messageService);
    }

    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/timerush");
    }

    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
        registry.addEndpoint("/messaging").setAllowedOrigins("*");
    }
}
