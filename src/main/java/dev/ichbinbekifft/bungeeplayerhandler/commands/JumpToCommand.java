package dev.ichbinbekifft.bungeeplayerhandler.commands;

import dev.ichbinbekifft.bungeeplayerhandler.BungeePlayerHandler;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class JumpToCommand extends Command {

    public JumpToCommand() {
        super("jumpTo");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {



        if (sender instanceof ProxiedPlayer) {
            final ProxiedPlayer proxiedPlayer = (ProxiedPlayer) sender;
            if (proxiedPlayer.hasPermission("jumpto")) {

                if(args.length!=1) {
                    proxiedPlayer.sendMessage(BungeePlayerHandler.getInstance().getPrefix() +
                            "Nutze §8/§7jumpto §8<§7player§8>");
                }

                ProxiedPlayer targetedPlayer = ProxyServer.getInstance().getPlayer(args[0]);

                if (targetedPlayer == null) {

                    proxiedPlayer.sendMessage(BungeePlayerHandler.getInstance().getPrefix() +
                            "Der gesuchte Nutzer ist nicht online!");

                } else {

                    proxiedPlayer.connect(targetedPlayer.getServer().getInfo());

                }

            } else {

                proxiedPlayer.sendMessage(BungeePlayerHandler.getInstance().getPrefix() +
                        "Duzu hast du keine Rechte!");

            }
        } else {

            sender.sendMessage(BungeePlayerHandler.getInstance().getPrefix() +
                    "Du must den Command als Spieler benutzen!");

        }

    }
}
