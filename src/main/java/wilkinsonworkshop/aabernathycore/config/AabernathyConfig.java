package wilkinsonworkshop.aabernathycore.config;

import org.bukkit.plugin.java.JavaPlugin;

public class AabernathyConfig {
    static private boolean configLoaded = false;

    static private JavaPlugin        parent;
    /**
     * Statically load plugin configuration, setting defaults
     * and validating incoming values.
     * @param plugin Main plugin instance.
     */
    public static void loadStatic(JavaPlugin plugin) {
        if (configLoaded) return;

        setStaticValues(plugin);
        setDefaultValues();
        configLoaded = true;
    }

    /**
     * Perform configuration related tear-down procedures.
     */
    public static void dumpStatic() {
        parent.saveConfig();
        configLoaded = false;
    }

    /**
     * Perform configuration related reload procedures.
     */
    public static void reloadStatic() {
        parent.reloadConfig();
    }

    private static void setDefaultValues() {
        parent.saveDefaultConfig();
    }

    private static void setStaticValues(JavaPlugin plugin) {
        parent = plugin;
        parent.getConfig();
    }
}
