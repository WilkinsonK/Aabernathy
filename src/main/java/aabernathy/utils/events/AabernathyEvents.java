package aabernathy.utils.events;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AabernathyEvents {

    static private JavaPlugin    m_plugin;
    static private PluginManager m_pluginManager;

    public static void setPlugin(JavaPlugin plugin) {
        m_plugin = plugin;
    }

    public static void setPluginManager(PluginManager pluginManager) {
        m_pluginManager = pluginManager;
    }

    /**
     * Statically load/register an event listener to the target plugin main
     * @param plugin Main plugin instance.
     * @param listener event listener to register.
     */
    public static void loadStatic(JavaPlugin plugin, AabernathyListener listener) {
        loadStatic(plugin, new AabernathyListener[]{listener});
    }

    /**
     * Statically load/register event listeners to the target plugin main
     * @param plugin Main plugin instance.
     * @param listeners event listeners to register.
     */
    public static void loadStatic(JavaPlugin plugin, AabernathyListener... listeners) {
        setPlugin(plugin);
        setPluginManager(plugin.getServer().getPluginManager());
        registerListeners(listeners);
    }

    /**
     * Register a single event `AabernathyEvents` object as
     * an event listener.
     * @param listener AabernathyEvents instance.
     */
    public static void registerListener(AabernathyListener listener) {
        m_pluginManager.registerEvents(listener, m_plugin);
        listener.toggleRegistered();
    }

    /**
     * Register multiple listeners using `registerListener()`
     * @param listeners AabernathyEvents instance.
     */
    public static void registerListeners(AabernathyListener... listeners) {
        for (AabernathyListener listener : listeners) {
            if (listener.isRegistered()) continue;
            registerListener(listener);
        }
    }
}
