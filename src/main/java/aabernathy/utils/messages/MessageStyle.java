package aabernathy.utils.messages;

import org.bukkit.ChatColor;

public record MessageStyle(ChatColor... styles) implements MessageComponent {

    public String digest() { return this.toString(); }

    public String toString() {
        StringBuilder preRender = new StringBuilder();
        for (ChatColor style : this.styles) {
            preRender.append(style.toString());
        }
        return preRender.toString();
    }

    static MessageStyle fromStyles(ChatColor... styles) {
        return new MessageStyle(styles);
    }
}
