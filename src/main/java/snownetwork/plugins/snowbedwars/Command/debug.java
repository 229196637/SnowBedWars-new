package snownetwork.plugins.snowbedwars.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import snownetwork.plugins.snowbedwars.Manage.ConfigManage;
import snownetwork.plugins.snowbedwars.SnowBedwars;

public class debug implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p =(Player)sender;
            if(p.hasPermission("snow.admin")){
                p.sendMessage("§c你没有权限！");
            }else {
                if(!ConfigManage.getConfiguration().getBoolean("dbug")){
                    ConfigManage.getConfiguration().set("debug",true);
                    SnowBedwars.setDebug(true);
                    SnowBedwars.getSnowBedWars().saveConfig();
                    p.sendMessage("§a你已经开启debug");
                }else if(ConfigManage.getConfiguration().getBoolean("debug")){
                    ConfigManage.getConfiguration().set("debug",false);
                    SnowBedwars.setDebug(false);
                    SnowBedwars.getSnowBedWars().saveConfig();
                    p.sendMessage("§a你已经关闭debug");
                }
            }
        }
        return false;
    }
}
