package fun.delson.delhomes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fun.delson.delhomes.commands.DelHome;
import fun.delson.delhomes.commands.HomeCmd;
import fun.delson.delhomes.commands.Homes;
import fun.delson.delhomes.commands.SetHome;
import fun.delson.delhomes.listeners.PlayerJoin;
import fun.delson.delhomes.listeners.PlayerLeave;
import fun.delson.delhomes.utils.PlayerConfigUtils;


public class DeLHomes extends JavaPlugin {
    
    @Override
    public void onEnable() {

        // Listeners
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);

        // Commands
        getCommand("home").setExecutor(new HomeCmd());
        getCommand("homes").setExecutor(new Homes());
        getCommand("sethome").setExecutor(new SetHome());
        getCommand("delhome").setExecutor(new DelHome());

        // Config
        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerConfigUtils.loadConfig(p);
        }

    }

    @Override
    public void onDisable() {

        // Config
        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerConfigUtils.writeConfig(p);
            PlayerConfigUtils.removePlayerConfig(p);
        }

    }

}

