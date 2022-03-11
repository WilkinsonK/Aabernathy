package wilkinsonworkshop.aabernathycore.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import wilkinsonworkshop.utils.messages.MessageObject;
import wilkinsonworkshop.utils.messages.MessageTemplate;

import java.util.HashMap;
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
                this.renderMOTD("Did you know, the server has multiple ways to connect"),
                this.renderMOTD("with other players of the community?? Click on the below"),
                this.renderMOTD("to see all that Aabernathy has to offer:"),
                this.renderLinkMessage("DISCORD", "https://discord.gg/G5mtAd8a8a"),
                this.renderLinkMessage("DYNMAP", "http://mine.wilkinson-workshop.tech/map/")
        );
    }

    private void dismissPlayer(PlayerQuitEvent event, Player player) {
        String name = player.getDisplayName();
        event.setQuitMessage(this.renderQuitMessage(name));
    }

    private String renderGreeting(String name) {
        HashMap<String, MessageObject<String>> map = getPlayerMap(name, ChatColor.GREEN);
        return playerGreeting.render(map);
    }

    private String renderMOTD(String message) {
        HashMap<String, MessageObject<String>> map = new HashMap<>();
        map.put("message", new MessageObject<>(message));
        return motdGreeting.render(map);
    }

    private String renderLinkMessage(String id, String uri) {
        HashMap<String, MessageObject<String>> map = new HashMap<>();
        map.put("id", new MessageObject<>(id, ChatColor.GRAY));
        map.put("uri", new MessageObject<>(uri, ChatColor.GRAY, ChatColor.UNDERLINE));
        return linkTemplate.render(map);
    }

    private String renderQuitMessage(String name) {
        MessageTemplate template = getMessageTemplate(quitMessages);
        HashMap<String, MessageObject<String>> map = getPlayerMap(name, ChatColor.RED);
        return template.render(map);
    }

    private String renderJoinMessage(String name) {
        MessageTemplate template = getMessageTemplate(joinMessages);
        HashMap<String, MessageObject<String>> map = getPlayerMap(name, ChatColor.GREEN);
        return template.render(map);
    }

    private static HashMap<String, MessageObject<String>> getPlayerMap(String name, ChatColor... style) {
        HashMap<String, MessageObject<String>> map = new HashMap<>();
        map.put("player", new MessageObject<>(name, style));
        return map;
    }

    private static final MessageTemplate playerGreeting = new MessageTemplate("Hello {player}, Welcome to Aabernathy!");
    private static final MessageTemplate motdGreeting = new MessageTemplate("{message}", ChatColor.GRAY);
    private static final MessageTemplate linkTemplate = new MessageTemplate("{id}: {uri}", ChatColor.GRAY);

    private static final MessageTemplate[] joinMessages = {
            new MessageTemplate("{player} materializes from out of nowhere!"),
            new MessageTemplate("Appearing from the void, {player} makes an entrance!"),
            new MessageTemplate("{player} suddenly exists, just as they always have."),
            new MessageTemplate("A wild {player} appears!")
    };

    private static final MessageTemplate[] quitMessages = {
            new MessageTemplate("{player} vanishes into thin air..."),
            new MessageTemplate("And like that, {player} no longer exists."),
            new MessageTemplate("Light momentarily bends around the spot {player} once stood, they disappear without a trace!")
    };

    private static MessageTemplate getMessageTemplate(MessageTemplate[] array) {
        return array[new Random().nextInt(array.length)];
    }
}
