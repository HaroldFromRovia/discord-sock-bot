package ru.itis.kpfu.bentos.discordbootbot.config;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.itis.kpfu.bentos.discordbootbot.events.MessageReceived;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = "ru.itis.kpfu.bentos.discordbootbot")
@Slf4j
@Profile("dis")
public class DiscordConfig {

    @Autowired
    private ApplicationContext context;

    @Bean
    public JDA jda() throws LoginException {

        Collection<GatewayIntent> intents = new ArrayList<>();
        intents.add(GatewayIntent.GUILD_MEMBERS);
        intents.add(GatewayIntent.GUILD_EMOJIS);
        intents.add(GatewayIntent.GUILD_BANS);
        intents.add(GatewayIntent.GUILD_INVITES);
        intents.add(GatewayIntent.GUILD_VOICE_STATES);
        intents.add(GatewayIntent.GUILD_PRESENCES);
        intents.add(GatewayIntent.GUILD_MESSAGES);
        intents.add(GatewayIntent.GUILD_MESSAGE_REACTIONS);
        intents.add(GatewayIntent.GUILD_MESSAGE_TYPING);
        intents.add(GatewayIntent.DIRECT_MESSAGES);
        intents.add(GatewayIntent.DIRECT_MESSAGE_REACTIONS);
        intents.add(GatewayIntent.DIRECT_MESSAGE_TYPING);

        JDABuilder builder = JDABuilder.create("", intents);

        builder.setActivity(Activity.playing("Space Rangers HD: A War Apart"));
        builder.addEventListeners(context.getBean(MessageReceived.class));
        return builder.build();
    }

}
