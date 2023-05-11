package json.element.core.command.structure;

import json.element.core.util.ClassGetter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;

@Getter
@RequiredArgsConstructor
public class CommandLoader {
    private final Plugin plugin;
    private final String directory;

    public void sendPacket() {
        for (Class<?> clazz : ClassGetter.getClassesForPackage(plugin, directory))
            if (CommandBuilder.class.isAssignableFrom(clazz)) {
                try {
                    CommandBuilder command = (CommandBuilder) clazz.newInstance();
                    String commandName = getCommandName(clazz);

                    getCommandMap().register(plugin.getName(), new org.bukkit.command.Command(commandName) {
                        @Override
                        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                            return command.onCommand(sender, this, commandLabel, args);
                        }
                    });

                    System.out.println("Command '" + clazz.getSimpleName() + "' has been loaded successfully!");
                } catch (Exception ex) { System.out.println("Could not load command '" + clazz.getSimpleName() + "'!"); }
            }
    }

    protected CommandMap getCommandMap() {
        try {
            Method getCommandMapMethod = plugin.getServer().getClass().getMethod("getCommandMap");
            return (CommandMap) getCommandMapMethod.invoke(plugin.getServer());
        } catch (Exception ex) { ex.printStackTrace(); }
        return null;
    }

    protected String getCommandName(Class<?> clazz) {
        try {
            Method getNameMethod = clazz.getMethod("getName");
            return (String) getNameMethod.invoke(clazz.newInstance());
        } catch (Exception ex) { ex.printStackTrace(); }
        return null;
    }
}
