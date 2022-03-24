package aabernathy.utils.messages;


import org.bukkit.ChatColor;

public class MessageStyler implements MessageComponent {
    private MessageStyle styleBeginning;
    private MessageStyle styleTermination;

    public String digest() { return this.stylize(""); }

    private void initMessageStyler(MessageStyle beginning, MessageStyle ending) {
        this.styleBeginning   = beginning;
        this.styleTermination = ending;
    }

    public String stylize(String input) {
        String beginning   = this.styleBeginning.toString();
        String termination = this.styleTermination.toString();
        return String.format("%s%s%s", beginning, input, termination);
    }

    public MessageStyler(MessageStyle beginning, MessageStyle termination) {
        this.initMessageStyler(beginning, termination);
    }

    public MessageStyler(MessageStyle beginning) {
        MessageStyle termination = MessageStyle.fromStyles(ChatColor.RESET);
        this.initMessageStyler(beginning, termination);
    }

    public MessageStyler(ChatColor... styles) {
        MessageStyle beginning   = MessageStyle.fromStyles(styles);
        MessageStyle termination = MessageStyle.fromStyles(ChatColor.RESET);
        this.initMessageStyler(beginning, termination);
    }

    public MessageStyler() {
        MessageStyle beginning   = MessageStyle.fromStyles(ChatColor.WHITE);
        MessageStyle termination = MessageStyle.fromStyles(ChatColor.RESET);
        this.initMessageStyler(beginning, termination);
    }
}
