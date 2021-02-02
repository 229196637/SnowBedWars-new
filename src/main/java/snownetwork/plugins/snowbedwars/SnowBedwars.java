package snownetwork.plugins.snowbedwars;

import org.bukkit.plugin.java.JavaPlugin;
import snownetwork.plugins.snowbedwars.Command.TabComplete;
import snownetwork.plugins.snowbedwars.Command.bws;
import snownetwork.plugins.snowbedwars.Command.debug;
import snownetwork.plugins.snowbedwars.Manage.ConfigManage;
import snownetwork.plugins.snowbedwars.Manage.DataManage;
import snownetwork.plugins.snowbedwars.Manage.GameSetting;
import snownetwork.plugins.snowbedwars.cache.GameCache;
import snownetwork.plugins.snowbedwars.cache.SettingCache;
import snownetwork.plugins.snowbedwars.state.GameState;

public final class SnowBedwars extends JavaPlugin {
    private static SnowBedwars s;
    private static Boolean debug;
    private static GameCache gameCache;
    private static SettingCache settingCache;

    @Override
    public void onEnable() {
        s=this;
        getLogger().info("§b==============§e插件开始加载§b===================");
        DataManage.LoadData();
        getLogger().info("§a游戏数据加载成功！");
        ConfigManage.loadConfig();
        getCommand("bwdebug").setExecutor(new debug());
        getCommand("bws").setExecutor(new bws());
        getCommand("bws").setTabCompleter(new TabComplete());
        getLogger().info("§a成功加载指令");
        debug=ConfigManage.getConfiguration().getBoolean("debug");
        getLogger().info("§aConfig配置文件加载成功");
        GameSetting.LoadData();
        gameCache=new GameCache();
        settingCache=new SettingCache();
        getLogger().info("§a游戏设置加载成功");
        GameState.setBedWarsState(GameState.Waiting);
        getLogger().info("§a开始游戏状态：§e等待...");
        getLogger().info("§b[起床战争]：§a插件成功加载！");

    }

    @Override
    public void onDisable() {
        getLogger().info("§b[起床战争]：§a插件成功卸载！");
    }

    public static SnowBedwars getSnowBedWars() {
        return s;
    }

    public static Boolean getDebug() {
        return debug;
    }

    public static void setDebug(Boolean debug) {
        SnowBedwars.debug = debug;
    }
    //检测游戏是否设置
    public static boolean iscomplete(){
        if(DataManage.getGamedata().getKeys(false)==null){
            return false;
        }else {
            return true;
        }
    }

    public static GameCache getGameCache() {
        return gameCache;
    }

    public static SettingCache getSettingCache() {
        return settingCache;
    }
}
