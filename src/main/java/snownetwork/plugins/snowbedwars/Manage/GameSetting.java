package snownetwork.plugins.snowbedwars.Manage;

import org.bukkit.configuration.file.YamlConfiguration;
import snownetwork.plugins.snowbedwars.SnowBedwars;

import java.io.File;
import java.io.IOException;

public class GameSetting {
    private static YamlConfiguration gamedata;
    public static void LoadData(){
        File file = new File(SnowBedwars.getSnowBedWars().getDataFolder(),"GameSetting.yml");
        if(!file.exists()){
            SnowBedwars.getSnowBedWars().saveResource("GameSetting.yml",false);

        }
        gamedata = YamlConfiguration.loadConfiguration(file);
    }

    public static YamlConfiguration getGamedata() {
        return gamedata;
    }
    public static void SaveGameSetting(){
        File file = new File(SnowBedwars.getSnowBedWars().getDataFolder(),"GameSetting.yml");
        try {
            gamedata.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
