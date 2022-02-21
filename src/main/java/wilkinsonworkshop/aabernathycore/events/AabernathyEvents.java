package wilkinsonworkshop.aabernathycore.events;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AabernathyEvents implements Listener {
    private boolean registered = false;

    static private JavaPlugin    parent;
    static private PluginManager pluginManager;

    /**
     * Statically load/register event listeners to the target plugin main
     * @param plugin Main plugin instance.
     * @param listeners event listeners to register.
     */
    public static void loadStatic(JavaPlugin plugin, AabernathyEvents[] listeners) {
        parent        = plugin;
        pluginManager = plugin.getServer().getPluginManager();
        registerListeners(listeners);
    }

    /**
     * Statically load/register an event listener to the target plugin main
     * @param plugin Main plugin instance.
     * @param listener event listener to register.
     */
    public static void loadStatic(JavaPlugin plugin, AabernathyEvents listener) {
        loadStatic(plugin, new AabernathyEvents[]{listener});
    }

    /**
     * Register a single event `AabernathyEvents` object as
     * an event listener.
     * @param listener AabernathyEvents instance.
     */
    public static void registerListener(AabernathyEvents listener) {
        pluginManager.registerEvents(listener, parent);
        listener.registered = true;
    }

    /**
     * Register multiple listeners using `registerListener()`
     * @param listeners AabernathyEvents instance.
     */
    public static void registerListeners(AabernathyEvents[] listeners) {
        for (AabernathyEvents listener : listeners) {
            if (listener.registered) continue;
            registerListener(listener);
        }
    }
}
