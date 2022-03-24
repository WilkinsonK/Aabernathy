package aabernathy.utils.messages;

import org.bukkit.ChatColor;

import java.util.Map;

public class MessageTemplate {
    private String templateScaffolding; // Skeleton used for message render.
    private MessageStyle templateStyle; // Styling used on message resets.

    /**
     * Given a keyword -> object mapping, replace any keywords in
     * the template that matches with the object related to that
     * keyword.
     * @param mapping keyword to object map.
     * @param <T> any type for values passed to map.
     * @return Rendered string from MessageTemplate.
     */
    public <T> String render(Map<String, T> mapping) {
        String message = this.templateScaffolding;

        for (String key : mapping.keySet()) {
            message = replaceKeys(message, key, mapping.get(key));
        }
        return String.format("%s%s", message, this.templateStyle.digest());
    }

    private static <T> String replaceKeys(String message, String keyword, T object) {
        return message.replaceAll(
                consumeObject(new MapKeyWord(keyword)),
                consumeObject(object)
        );
    }

    /**
     * Break a given object down to a String.
     * @param object object to Stringify.
     * @param <T> any type.
     * @return Stringifies object
     */
    private static <T> String consumeObject(T object) {
        if (object instanceof MessageComponent) {
            return ((MessageComponent) object).digest();
        } else {
            return object.toString();
        }
    }

    private void initMessageTemplate(String template, MessageStyle style) {
        this.templateScaffolding = template;
        this.templateStyle       = style;
    }

    public MessageTemplate(String template) {
        this.initMessageTemplate(
                template,
                MessageStyle.fromStyles(ChatColor.RESET)
        );
    }

    public MessageTemplate(String template, MessageStyle style) {
        this.initMessageTemplate(template, style);
    }

    public MessageTemplate(String template, ChatColor... styles) {
        this.initMessageTemplate(template, MessageStyle.fromStyles(styles));
    }
}

class MapKeyWord {
    private static final String formatString = "\\{%s\\}"; // to initialize keyword as.
    private final String keyword;

    public String toString() {
        return this.keyword;
    }

    public MapKeyWord(String word) {
        this.keyword = String.format(formatString, word);
    }
}
