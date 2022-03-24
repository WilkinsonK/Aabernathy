package aabernathy.utils.messages;

import org.bukkit.ChatColor;

public class MessageObject<T> implements MessageComponent {
    private T object;
    private MessageStyler styler;

    public String digest() {
        return this.styler.stylize(this.object.toString());
    }

    private void initMessageObject(T object, MessageStyler styler) {
        this.styler = styler;
        this.object = object;
    }

    public MessageObject(T object) {
        this.initMessageObject(object, new MessageStyler());
    }

    public MessageObject(T object, MessageStyler styler) {
        this.initMessageObject(object, styler);
    }

    public MessageObject(T object, ChatColor... styles) {
        this.initMessageObject(object, new MessageStyler(styles));
    }
}
