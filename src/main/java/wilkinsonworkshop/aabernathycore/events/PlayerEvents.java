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

        s_greetPlayer(event, player);
    }

    @EventHandler
    public static void onPlayerExit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        s_dismissPlayer(event, player);
    }

    private static void s_greetPlayer(PlayerJoinEvent event, Player player) {
        String name = player.getDisplayName();
        event.setJoinMessage(s_renderJoinMessage(name));

        player.sendMessage(
                s_renderGreeting(name),
                s_renderMOTD("Did you know, the server has multiple ways to connect"),
                s_renderMOTD("with other players of the community?? Click on the below"),
                s_renderMOTD("to see all that Aabernathy has to offer:"),
                s_renderLinkMessage("DISCORD", "https://discord.gg/G5mtAd8a8a"),
                s_renderLinkMessage("DYNMAP", "http://mine.wilkinson-workshop.tech/map/")
        );
    }

    private static void s_dismissPlayer(PlayerQuitEvent event, Player player) {
        String name = player.getDisplayName();
        event.setQuitMessage(s_renderQuitMessage(name));
    }

    private static String s_renderGreeting(String name) {
        HashMap<String, MessageObject<String>> map = s_getPlayerMap(name, ChatColor.GREEN);
        return playerGreeting.render(map);
    }

    private static String s_renderMOTD(String message) {
        HashMap<String, MessageObject<String>> map = new HashMap<>();
        map.put("message", new MessageObject<>(message, ChatColor.GRAY));
        return motdGreeting.render(map);
    }

    private static String s_renderLinkMessage(String id, String uri) {
        HashMap<String, MessageObject<String>> map = new HashMap<>();
        map.put("id", new MessageObject<>(id, ChatColor.GRAY));
        map.put("uri", new MessageObject<>(uri, ChatColor.GRAY, ChatColor.UNDERLINE));
        return linkTemplate.render(map);
    }

    private static String s_renderQuitMessage(String name) {
        MessageTemplate template = s_getMessageTemplate(quitMessages);
        HashMap<String, MessageObject<String>> map = s_getPlayerMap(name, ChatColor.RED);
        return template.render(map);
    }

    private static String s_renderJoinMessage(String name) {
        MessageTemplate template = s_getMessageTemplate(joinMessages);
        HashMap<String, MessageObject<String>> map = s_getPlayerMap(name, ChatColor.GREEN);
        return template.render(map);
    }

    private static HashMap<String, MessageObject<String>> s_getPlayerMap(String name, ChatColor... style) {
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

    private static MessageTemplate s_getMessageTemplate(MessageTemplate[] array) {
        return array[new Random().nextInt(array.length)];
    }
}
