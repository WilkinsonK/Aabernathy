package wilkinsonworkshop.aabernathycore.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Random;

public class PlayerEvents extends AabernathyEvents {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new PlayerEvents().greetPlayer(event, player);
    }

    @EventHandler
    public static void onPlayerExit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        new PlayerEvents().dismissPlayer(event, player);
    }

    private void greetPlayer(PlayerJoinEvent event, Player player) {
        String name = player.getDisplayName();
        event.setJoinMessage(this.renderJoinMessage(name));
        player.sendMessage(
                this.renderGreeting(name),
                MessageTemplate.withColor("Did you know, the server uses the Dynmap plugin??", ChatColor.DARK_GRAY),
                MessageTemplate.withColor("The world map can be accessed from here: ", ChatColor.DARK_GRAY),
                MessageTemplate.withColor(ChatColor.UNDERLINE + "http://mine.wilkinson-workshop.tech", ChatColor.DARK_GRAY)
        );
    }

    private void dismissPlayer(PlayerQuitEvent event, Player player) {
        String name = player.getDisplayName();
        event.setQuitMessage(this.renderQuitMessage(name));
    }

    private String renderGreeting(String name) {
        return String.format("Hello %s, Welcome to Aabernathy!", MessageTemplate.withColor(name, ChatColor.GREEN));
    }

    private String renderQuitMessage(String name) {
        MessageTemplate template = getMessageTemplate(quitMessages);
        return template.renderMessage(MessageTemplate.withColor(name, ChatColor.RED));
    }

    private String renderJoinMessage(String name) {
        MessageTemplate template = getMessageTemplate(joinMessages);
        return template.renderMessage(MessageTemplate.withColor(name, ChatColor.GREEN));
    }

    private static final MessageTemplate[] joinMessages = {
            new MessageTemplate("%s materializes from out of nowhere!"),
            new MessageTemplate("Appearing from the void, %s, makes an entrance!"),
            new MessageTemplate("%s suddenly exists, just as they always have.")
    };

    private static final MessageTemplate[] quitMessages = {
            new MessageTemplate("%s vanishes into thin air..."),
            new MessageTemplate("And like that, %s no longer exists."),
            new MessageTemplate("Light momentarily bends around the spot %s once stood, they disappear without a trace!")
    };

    private static MessageTemplate getMessageTemplate(MessageTemplate[] array) {
        return array[new Random().nextInt(array.length)];
    }
}

class MessageTemplate {
    private String template;

    public MessageTemplate(String template) {
        this.template = template;
    }

    public String renderMessage(String message) {
        return String.format(this.template, message);
    }

    public static String withColor(String value, ChatColor color) {
        return String.format("%s%s%s", color, value, ChatColor.RESET);
    }
}
