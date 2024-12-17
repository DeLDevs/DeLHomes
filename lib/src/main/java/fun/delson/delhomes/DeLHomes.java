package fun.delson.delhomes;

import org.bukkit.plugin.java.JavaPlugin;

import fun.delson.delhomes.commands.DelHome;
import fun.delson.delhomes.commands.SetHome;
import fun.delson.delhomes.listeners.PlayerJoin;


public class DeLHomes extends JavaPlugin {
    
    @Override
    public void onEnable() {

        // Listeners
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

        // Commands
        getCommand("sethome").setExecutor(new SetHome());
        getCommand("delhome").setExecutor(new DelHome());

    }

}

