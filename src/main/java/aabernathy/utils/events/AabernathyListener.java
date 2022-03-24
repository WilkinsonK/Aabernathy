package aabernathy.utils.events;

import org.bukkit.event.Listener;

public abstract class AabernathyListener implements Listener {
    private boolean m_isRegistered = false;

    /**
     * Toggles whether the listener instance
     * has been registered for event handling.
     */
    public void toggleRegistered() {
        this.m_isRegistered = !this.m_isRegistered;
    }

    /**
     * Determine if listener is registered.
     * @return boolean
     */
    public boolean isRegistered() {
        return this.m_isRegistered;
    }
}
