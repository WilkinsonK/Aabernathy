package aabernathy.plugin;

import aabernathy.utils.events.AabernathyEvents;
import aabernathy.utils.events.AabernathyListener;
import org.bukkit.plugin.java.JavaPlugin;
import aabernathy.plugin.events.PlayerEvents;

public final class App extends JavaPlugin {

    // Place event handlers/listeners here
    // to be registered.
    final private static AabernathyListener[] eventListeners = {
            new PlayerEvents(),
    };

    @Override
    public void onEnable() {
        // Plugin startup logic
        AabernathyEvents.loadStatic(this, eventListeners);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
