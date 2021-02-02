package snownetwork.plugins.snowbedwars.cache;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import snownetwork.plugins.snowbedwars.Manage.DataManage;

import java.util.ArrayList;
import java.util.HashMap;

public class GameCache {
    YamlConfiguration DataManage=null;
    public ArrayList<String> TeamIDList=null;
    public HashMap<String,String> TeamColour=null;
    public HashMap<String,String> TeamNamer=null;

    public HashMap<String, Block>TeamIron=null;
    public HashMap<String,Block>TeamGold=null;
    public ArrayList<Block>Emerald=null;
    public ArrayList<Block>Diamond=null;

    public HashMap<String,Block>TeamUShop=null;
    public HashMap<String,Block>TeamCShop=null;

    public HashMap<String,Block>TeamBed=null;
    public Location lobby=null;


    public GameCache(){
        DataManage= snownetwork.plugins.snowbedwars.Manage.DataManage.getGamedata();
    }
}
