package dev.ichbinbekifft.bungeeplayerhandler;

import dev.ichbinbekifft.bungeeplayerhandler.commands.*;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;

public final class BungeePlayerHandler extends Plugin {

    /**
     *
     * This File was created by
     * @author IchbinBekifft
     * date: 7.7.2021
     */

    private static BungeePlayerHandler instance;
    private String prefix;

    @Override
    public void onEnable() {


        instance = this;
        this.prefix = "§8[§6BungeeCord§8] §7";


        /**
         *
         * Register Commands
         */

        final PluginManager pluginManager = getProxy().getPluginManager();

        pluginManager.registerCommand(this, new JumpToCommand());
        pluginManager.registerCommand(this, new JoinMeCommand());

        pluginManager.registerCommand(this, new PingCommand());
        pluginManager.registerCommand(this, new TeamChatCommand());
        pluginManager.registerCommand(this, new SendCommand());

    }

    public String getPrefix() {
        return prefix;
    }

    public static BungeePlayerHandler getInstance() {
        return instance;
    }
}
