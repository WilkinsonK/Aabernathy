package wilkinsonworkshop.aabernathycore.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
                withColor("Did you know, the server uses the Dynmap plugin??", ChatColor.DARK_GRAY),
                withColor("The world map can be accessed from here: ", ChatColor.DARK_GRAY),
                withColor(ChatColor.UNDERLINE + "http://mine.wilkinson-workshop.tech", ChatColor.DARK_GRAY)
        );
    }

    private void dismissPlayer(PlayerQuitEvent event, Player player) {
        String name = player.getDisplayName();
        event.setQuitMessage(this.renderQuitMessage(name));
    }

    private String renderGreeting(String name) {
        return String.format("Hello %s, Welcome to Aabernathy!", withColor(name, ChatColor.GREEN));
    }

    private String renderQuitMessage(String name) {
        return String.format("%s vanishes into thin air...", withColor(name, ChatColor.RED));
    }

    private String renderJoinMessage(String name) {
        return String.format("%s materializes from out of nowhere!", withColor(name, ChatColor.GREEN));
    }

    private static String withColor(String value, ChatColor color) {
        return String.format("%s%s%s", color, value, ChatColor.RESET);
    }
}
