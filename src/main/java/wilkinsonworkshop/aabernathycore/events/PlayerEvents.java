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

        MessageTemplate template = new MessageTemplate("%s", ChatColor.GRAY);
        player.sendMessage(
                this.renderGreeting(name),
                template.render("Did you know, the server uses the Dynmap plugin??"),
                template.render("See Aabernathy in all of its glory by clicking here:"),
                template.render("http://mine.wilkinson-workshop.tech", ChatColor.UNDERLINE)
        );
    }

    private void dismissPlayer(PlayerQuitEvent event, Player player) {
        String name = player.getDisplayName();
        event.setQuitMessage(this.renderQuitMessage(name));
    }

    private String renderGreeting(String name) {
        return new MessageTemplate("Hello %s, Welcome to Aabernathy!").render(name, ChatColor.GREEN);
    }

    private String renderQuitMessage(String name) {
        MessageTemplate template = getMessageTemplate(quitMessages);
        return template.render(name, ChatColor.RED);
    }

    private String renderJoinMessage(String name) {
        MessageTemplate template = getMessageTemplate(joinMessages);
        return template.render(name, ChatColor.GREEN);
    }

    private static final MessageTemplate[] joinMessages = {
            new MessageTemplate("%s materializes from out of nowhere!"),
            new MessageTemplate("Appearing from the void, %s makes an entrance!"),
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
    private String templateStyling;

    @Override
    public String toString() {
        return this.render("");
    }

    public MessageTemplate() {
        this.initMessageTemplate("%s", ChatColor.RESET.toString());
    }

    public MessageTemplate(String template) {
        this.initMessageTemplate(template, ChatColor.RESET.toString());
    }

    public MessageTemplate(String template, ChatColor... styles) {
        this.initMessageTemplate(template, renderStyle(styles));
    }

    private void initMessageTemplate(String template, String templateStyling) {
        this.templateStyling = templateStyling;
        this.template        = this.withStyle(template, this.templateStyling);
    }

    public String render(String message) {
        String rend = this.withStyle(this.template, this.templateStyling);
        return String.format(rend, message);
    }

    public String render(String message, ChatColor... styles) {
        message = this.withStyle(message, styles);
        return this.render(message);
    }

    private static String renderStyle(ChatColor... styles) {
        StringBuilder prerender = new StringBuilder();
        for (Object style : styles) {
            prerender.append(style.toString());
        }
        return prerender.toString();
    }

    public String withStyle(String value, ChatColor... styling) {
        return this.withStyle(value, renderStyle(styling));
    }

    private String withStyle(String value, String styling) {
        if (value.length() < 1) { return value; }
        return String.format("%s%s%s", styling, value, this.templateStyling);
    }
}
