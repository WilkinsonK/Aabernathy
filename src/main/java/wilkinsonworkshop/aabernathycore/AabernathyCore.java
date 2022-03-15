package wilkinsonworkshop.aabernathycore;

import org.bukkit.plugin.java.JavaPlugin;
import wilkinsonworkshop.aabernathycore.config.AabernathyConfig;
import wilkinsonworkshop.aabernathycore.events.AabernathyEvents;
import wilkinsonworkshop.aabernathycore.events.PlayerEvents;

public final class AabernathyCore extends JavaPlugin {

    // Place event handlers/listeners here
    // to be registered.
    final private static AabernathyEvents[] eventListeners = {
            new PlayerEvents(),
    };

    @Override
    public void onEnable() {
        // Plugin startup logic
        AabernathyConfig.loadStatic(this);
        AabernathyEvents.loadStatic(this, eventListeners);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        AabernathyConfig.dumpStatic();
    }
}
