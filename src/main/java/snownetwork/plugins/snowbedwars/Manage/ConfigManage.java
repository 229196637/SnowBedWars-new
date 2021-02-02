package snownetwork.plugins.snowbedwars.Manage;

import org.bukkit.configuration.file.FileConfiguration;
import snownetwork.plugins.snowbedwars.SnowBedwars;

public class ConfigManage {
    private static FileConfiguration configuration;
    public static void loadConfig(){
        SnowBedwars.getSnowBedWars().getConfig().options().copyDefaults();
        SnowBedwars.getSnowBedWars().getSnowBedWars().saveDefaultConfig();
        SnowBedwars.getSnowBedWars().getSnowBedWars().reloadConfig();
        configuration=SnowBedwars.getSnowBedWars().getConfig();
    }

    public static FileConfiguration getConfiguration() {
        return configuration;
    }
}
