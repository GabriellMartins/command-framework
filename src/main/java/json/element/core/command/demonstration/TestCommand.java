package json.element.core.command.demonstration;

import json.element.core.command.structure.CommandBuilder;
import json.element.core.command.structure.CommandHelper;
import org.bukkit.command.CommandSender;

public class TestCommand extends CommandBuilder {
    public TestCommand() {
        super("test", "", new String[]{ "" }, false);
    }

    @Override
    public void handle(CommandHelper helper) {
        String[] args = helper.getArgs();
        CommandSender sender = helper.getSender();

        if (args.length < 1)
            sender.sendMessage("it's working :)");
    }
}
