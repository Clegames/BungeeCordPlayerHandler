package dev.ichbinbekifft.bungeeplayerhandler.commands;

import dev.ichbinbekifft.bungeeplayerhandler.BungeePlayerHandler;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PingCommand extends Command {
    public PingCommand() {
        super("ping");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {

            final ProxiedPlayer proxiedPlayer = (ProxiedPlayer) sender;
            proxiedPlayer.sendMessage(BungeePlayerHandler.getInstance().getPrefix() + "Dein Ping beträgt: " +
                    (proxiedPlayer.getPing() >= 50 ? "§e" +
                            proxiedPlayer.getPing() :
                            (proxiedPlayer.getPing() >=
                                    100 ? "§4" + proxiedPlayer.getPing() : "§a" +
                                    proxiedPlayer.getPing())) + " §7ms§8!");



        } else {

            sender.sendMessage(BungeePlayerHandler.getInstance().getPrefix() +
                    "Du kannst den Command nur als Spieler ausführen§8!");

        }

    }
}
