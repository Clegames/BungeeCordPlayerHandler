package dev.ichbinbekifft.bungeeplayerhandler.commands;

import dev.ichbinbekifft.bungeeplayerhandler.BungeePlayerHandler;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SendCommand extends Command {

    public SendCommand() {
        super("send");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {

            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (!player.hasPermission("core.send")) {
                player.sendMessage(BungeePlayerHandler.getInstance().getPrefix() + "Duzu hast du keine Rechte§8!");
                return;
            }

            if (strings.length != 2) {
                player.sendMessage(BungeePlayerHandler.getInstance().getPrefix() +
                        "Nutze bitte: §8/send §8<§7player§8> <§7server§8>");
                return;
            }


            ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(strings[1]);

            if (serverInfo == null) {
                player.sendMessage(BungeePlayerHandler.getInstance().getPrefix()
                        + "Dieser Spieler konnte nicht gefunden werden§8.");
                return;
            }

            if (strings[0].equalsIgnoreCase("*")) {

                for (ProxiedPlayer target : ProxyServer.getInstance().getPlayers()) {

                    target.connect(serverInfo);
                    target.sendMessage(BungeePlayerHandler.getInstance().getPrefix() +
                            "Du wurdest von " + player.getName() +
                            " auf den Server " + serverInfo.getName() + " gesendet§8!");
                    player.sendMessage(BungeePlayerHandler.getInstance().getPrefix() +
                            "Der Spieler " + target.getName()
                            + " wurde §aerfolgreich §7auf "
                            + serverInfo.getName() + " gesendet§8!");
                }

                return;
            }

            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);

            if (target == null) {
                player.sendMessage(BungeePlayerHandler.getInstance().getPrefix() +
                        "Der Spieler §4nicht §7gefunden werden!");
                return;
            }


            target.connect(serverInfo);
            target.sendMessage(BungeePlayerHandler.getInstance().getPrefix() +
                    "Du wurdest von §6" + player.getName() + " §7auf " + serverInfo.getName() +
                    " gesendet!");
            player.sendMessage(BungeePlayerHandler.getInstance().getPrefix() +
                    "Der Spieler " + target.getName() + " wurde §aerfolgreich §7auf " +
                    serverInfo.getName() + " gesendet!");


        }
    }
}
