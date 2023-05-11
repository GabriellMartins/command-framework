package json.element.core.command.structure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Getter
@RequiredArgsConstructor
public abstract class CommandBuilder implements CommandExecutor {
    private final String name;
    private final String permission;
    private final String[] aliases;
    private final boolean onlyEntities;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (isOnlyEntities() && (!(commandSender instanceof Player)) || (commandSender instanceof Player) && !commandSender.hasPermission(permission)) {
            commandSender.sendMessage(ChatColor.RED + "Unable to perform this operation.");
            return true;
        }

        handle(new CommandHelper(commandSender, label, args));
        return false;
    }

    public abstract void handle(CommandHelper helper);
}
