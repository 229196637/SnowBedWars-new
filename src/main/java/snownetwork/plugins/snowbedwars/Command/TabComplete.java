package snownetwork.plugins.snowbedwars.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("bws")) {
            List<String> result = new ArrayList<>();
            if(args.length==1){
                result.add("create");
                result.add("setlobby");
                result.add("createteam");
                result.add("createshop");
                result.add("sethome");
                result.add("setbed");
                result.add("setIG");
                result.add("setDE");
                result.add("save");
                return result;
            }else if(args.length>1){
                if(args[0].equalsIgnoreCase("createshop")){
                    if(args.length==2){
                        return bws.TeamList;
                    }else if(args.length==3){
                        result.add("U");
                        result.add("C");
                        return result;
                    }
                }else if(args[0].equalsIgnoreCase("sethome")){
                    return bws.TeamList;
                }else if(args[0].equalsIgnoreCase("setbed")){
                    return bws.TeamList;
                }else if(args[0].equalsIgnoreCase("setIG")){
                    if(args.length==2){
                        return bws.TeamList;
                    }else if(args.length==3){
                        result.add("I");
                        result.add("G");
                        return result;
                    }
                }
            }
        }
        return null;
    }
}
