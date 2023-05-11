package json.element.core;

import json.element.core.command.structure.CommandLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class Framework extends JavaPlugin {

    @Override
    public void onEnable() {
        new CommandLoader(this, "json.element.core.command.demonstration").sendPacket();
    }
}
