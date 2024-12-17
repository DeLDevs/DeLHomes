package fun.delson.delhomes.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fun.delson.delhomes.config.Home;
import fun.delson.delhomes.config.PlayerConfig;

public class PlayerConfigUtils {

    public static void createConfig(Player player) {
        File file = new File(PluginUtils.getPlugin().getDataFolder() + "/players/" + player.getName() + ".json");
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PlayerConfig playerConfig = new PlayerConfig(player, new Home[0]);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
        if (findPlayerConfig(player) != -1) {
            createConfig(player);
        }
        File file = getPlayerFile(player);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        String json = scanner.useDelimiter("\\A").next();
        scanner.close();
        Gson gson = new Gson();
        PlayerConfig playerConfig = gson.fromJson(json, PlayerConfig.class);
        playerConfigs.addLast(playerConfig);
    }

    public static void writeConfig(Player player) {
        PlayerConfig playerConfig = playerConfigs.get(findPlayerConfig(player));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(playerConfig);
        try {
            FileWriter fw = new FileWriter(getPlayerFile(player), false);
            fw.write(json);
            fw.close();
            if (findPlayerConfig(player) != -1) {
                playerConfigs.remove(findPlayerConfig(player));
            }
            loadConfig(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int findPlayerConfig(Player player) {
        for (int i = 0; i < playerConfigs.size(); i++) {
            if (Bukkit.getPlayer(playerConfigs.get(i).uuid) == player) {
                return i;
            }
        }
        return -1;
    }

    public static PlayerConfig getPlayerConfig(Player player) {
        if (findPlayerConfig(player) != -1) {
            return playerConfigs.get(findPlayerConfig(player));
        }
        return null;
    }

    public static File getPlayerFile(Player player) {
        File file = new File(PluginUtils.getPlugin().getDataFolder() + "/players/" + player.getName() + ".json");
        if (!file.exists()) {
            createConfig(player);
        }
        return file;
    }
}
