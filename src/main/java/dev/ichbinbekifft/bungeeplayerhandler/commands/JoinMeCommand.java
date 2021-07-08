package dev.ichbinbekifft.bungeeplayerhandler.commands;

import dev.ichbinbekifft.bungeeplayerhandler.BungeePlayerHandler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.HashMap;

public class JoinMeCommand extends Command {
    public JoinMeCommand() {
        super("joinMe");
    }

    HashMap<ProxiedPlayer, Long> playerJoinMeTime = new HashMap<>();

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;


            if(args.length==1) {
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

                if(target == null) {
                    return;
                }

                if(!playerJoinMeTime.containsKey(target)) {
                    return;
                }
                player.connect(target.getServer().getInfo());
                return;
            }

            if(!player.hasPermission("bungeecord.joinme")) {
                player.sendMessage(BungeePlayerHandler.getInstance().getPrefix() + "§c§lKeine Rechte§8!");
                return;
            }

            if(playerJoinMeTime.containsKey(player)) {
                if(playerJoinMeTime.get(player) >= System.currentTimeMillis()) {
                    player.sendMessage(BungeePlayerHandler.getInstance().getPrefix()
                            + "Du musst noch warten bis du wieder ein JoinMe ausführen kannst§8!");
                    return;
                }
                playerJoinMeTime.remove(player);
            }

            for(ProxiedPlayer target : ProxyServer.getInstance().getPlayers()) {

                target.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                        "§6" + player.getName() + " §7hat ein JoinMe erstellt§8!"));

                TextComponent textComponent = new TextComponent(BungeePlayerHandler.getInstance().getPrefix()
                        + player.getName() + " spielt " + player.getServer().getInfo().getName());
                textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/joinme " + player.getName()));
                target.sendMessage(textComponent);
            }

            playerJoinMeTime.put(player, System.currentTimeMillis() + (60000*15));

        }
    }
}
