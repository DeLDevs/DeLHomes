package fun.delson.delhomes.config;

import java.util.UUID;

import org.bukkit.Location;

public class Home {
    public String name;
    public UUID world;
    public String worldName;
    public double x, y, z;
    public float yaw, pitch;

    public Home(String name, Location location) {
        this.name = name;
        setLocation(location);
    }

    public void setLocation(Location location) {
        this.world = location.getWorld().getUID();
        this.worldName = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }
}

