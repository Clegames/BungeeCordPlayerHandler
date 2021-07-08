package dev.ichbinbekifft.bungeeplayerhandler.commands;

import dev.ichbinbekifft.bungeeplayerhandler.BungeePlayerHandler;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class TeamChatCommand extends Command {

    public TeamChatCommand() {
        super("tc");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if(commandSender instanceof ProxiedPlayer) {

            final ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if(!player.hasPermission("bungeecord.teamchat")) {
                player.sendMessage(BungeePlayerHandler.getInstance().getPrefix() + "§c§lDazu hast du keine Rechte§8!");
                return;
            }

            if(args.length == 0) {
                player.sendMessage(BungeePlayerHandler.getInstance().getPrefix() +
                        "Diese Teammitglieder sind online§8:");
                for (ProxiedPlayer target : ProxyServer.getInstance().getPlayers()) {
                    if(target.hasPermission("bungeecord.teamchat")) {
                        player.sendMessage(" §8➜ §a" + target.getName());
                    }
                }
                return;
            }

            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < args.length; i++){
                stringBuilder.append(args[i]).append(" ");
            }

            for (ProxiedPlayer target : ProxyServer.getInstance().getPlayers()) {
                if(target.hasPermission("bungeecord.teamchat")) {
                    target.sendMessage("§8[§6TeamChat§8] §7" + player.getName() + " §8➜§7 " + stringBuilder.toString());
                }
            }
        }
    }
}
