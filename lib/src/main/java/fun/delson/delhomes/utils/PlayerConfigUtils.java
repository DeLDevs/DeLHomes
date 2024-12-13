package fun.delson.delhomes.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.entity.Player;

public class PlayerConfigUtils {

    public static void createConfig(Player player) {
        File file = new File(PluginUtils.getPlugin().getDataFolder() + "/players/" + player.getName() + ".json");
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
