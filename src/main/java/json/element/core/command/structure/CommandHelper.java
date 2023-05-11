package json.element.core.command.structure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class CommandHelper {
    private final CommandSender sender;
    private final String label;
    private final String[] args;

    public Player getPlayer() { return (Player) getSender(); }
    public Player getTarget(String name) { return Bukkit.getPlayer(name); }
    public Player getTarget(UUID uniqueID) { return Bukkit.getPlayer(uniqueID); }

    public String structureFormat(String[] args, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = length; i < args.length; i++)
            builder.append(args[i]).append(" ");

        return builder.toString();
    }
}
