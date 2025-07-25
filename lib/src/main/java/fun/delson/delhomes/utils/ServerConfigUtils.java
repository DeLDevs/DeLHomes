package fun.delson.delhomes.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fun.delson.delhomes.config.Home;
import fun.delson.delhomes.config.ServerConfig;

public class ServerConfigUtils {

    public static void createConfig() {
        File file = new File(PluginUtils.getPlugin().getDataFolder(), "server.json");
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            PluginUtils.getPlugin().getLogger().severe("Failed to create plugin data folder: " + file.getAbsolutePath());
        }
        if (file.exists()) {
            return;
        }

        try {
            if (!file.createNewFile()) {
                PluginUtils.getPlugin().getLogger().warning("Server config file already exists or could not be created.");
            }
            ServerConfig serverConfig = new ServerConfig(new Home[0]);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter fw = new FileWriter(file)) {
                fw.write(gson.toJson(serverConfig));
            }
            PluginUtils.getPlugin().getLogger().info("Created server config file: " + file.getAbsolutePath());
        } catch (IOException e) {
            PluginUtils.getPlugin().getLogger().severe("Failed to create server config file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static ServerConfig serverConfig = null;

    public static void loadConfig() {
        File file = getServerFile();
        if (!file.exists()) {
            PluginUtils.getPlugin().getLogger().warning("Server config file does not exist, creating a new one.");
            createConfig();
            file = getServerFile();
        }
        try (Scanner scanner = new Scanner(file)) {
            String json = scanner.useDelimiter("\\A").next();
            Gson gson = new Gson();
            serverConfig = gson.fromJson(json, ServerConfig.class);
            PluginUtils.getPlugin().getLogger().info("Loaded server config successfully.");
        } catch (FileNotFoundException e) {
            PluginUtils.getPlugin().getLogger().severe("Server config file not found: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            PluginUtils.getPlugin().getLogger().severe("Failed to load server config: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void writeConfig() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(serverConfig);
        try {
            FileWriter fw = new FileWriter(getServerFile(), false);
            fw.write(json);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static File getServerFile() {
        File file = new File(PluginUtils.getPlugin().getDataFolder(), "server.json");
        if (!file.exists()) {
            createConfig();
        }
        return file;
    }

}

