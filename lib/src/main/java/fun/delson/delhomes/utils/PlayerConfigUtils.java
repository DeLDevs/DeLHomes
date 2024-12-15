package fun.delson.delhomes.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.google.gson.Gson;

import fun.delson.delhomes.config.Home;
import fun.delson.delhomes.config.PlayerConfig;

public class PlayerConfigUtils {

    public static void createConfig(Player player) {
        File file = new File(PluginUtils.getPlugin().getDataFolder() + "/players/" + player.getName() + ".json");
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PlayerConfig playerConfig = new PlayerConfig(player, new ArrayList<Home>());
        Gson gson = new Gson();
        String json = gson.toJson(playerConfig);
        try {
            FileWriter fw = new FileWriter(file, false);
            fw.write(json);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<PlayerConfig> playerConfigs = new ArrayList<PlayerConfig>();

    public static void loadConfig(Player player) {
        File file = getPlayerFile(player);
        if (findPlayerConfig(player) != -1) {
            return;
        }
        String json = file.toString();
        Gson gson = new Gson();
        PlayerConfig playerConfig = gson.fromJson(json, PlayerConfig.class);
        playerConfigs.addLast(playerConfig);
    }

    public static void writeConfig(Player player) {
        PlayerConfig playerConfig = playerConfigs.get(findPlayerConfig(player));
        Gson gson = new Gson();
        String json = gson.toJson(playerConfig);
        try {
            FileWriter fw = new FileWriter(getPlayerFile(player), false);
            fw.write(json);
            fw.close();
            playerConfigs.remove(findPlayerConfig(player));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int findPlayerConfig(Player player) {
        for (int i = 0; i < playerConfigs.size(); i++) {
            if (playerConfigs.get(i).player == player) {
                return i;
            }
        }
        return -1;
    }

    public static File getPlayerFile(Player player) {
        File file = new File(PluginUtils.getPlugin().getDataFolder() + "/players/" + player.getName() + ".json");
        if (!file.exists()) {
            createConfig(player);
        }
        return file;
    }
}
