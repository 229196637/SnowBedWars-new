package snownetwork.plugins.snowbedwars.Manage;

import org.bukkit.configuration.file.YamlConfiguration;
import snownetwork.plugins.snowbedwars.SnowBedwars;

import java.io.File;
import java.io.IOException;

public class DataManage {
    private static YamlConfiguration gamedata;
    public static void LoadData(){
        File file = new File(SnowBedwars.getSnowBedWars().getDataFolder(),"game.yml");
        if(!file.exists()){
            SnowBedwars.getSnowBedWars().saveResource("game.yml",false);

        }
        gamedata = YamlConfiguration.loadConfiguration(file);
    }

    public static YamlConfiguration getGamedata() {
        return gamedata;
    }
    public static void SaveGameSetting(){
        File file = new File(SnowBedwars.getSnowBedWars().getDataFolder(),"game.yml");
        try {
            gamedata.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
