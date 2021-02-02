package snownetwork.plugins.snowbedwars.Command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import snownetwork.plugins.snowbedwars.Manage.DataManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class bws implements CommandExecutor {
    YamlConfiguration g = null;
    ConfigurationSection game = null;
    ConfigurationSection TeamData = null;
    ConfigurationSection Location = null;
    HashMap<String, String> TeamShopIsSet = new HashMap<>();
    ArrayList<String> TeamhomeIsSet = new ArrayList<>();
    ArrayList<String> TeamBedIsSet = new ArrayList<>();
    int DiamondSize = 0;
    int EmeraldSize = 0;
    boolean IsSetLobby = false;
    boolean IscreateGame=false;
    HashMap<String, Integer> TeamIroncesIsSet = new HashMap<>();
    HashMap<String, Integer> TeamGoldcesIsSet = new HashMap<>();
    int Teamsize = 0;
    public static ArrayList<String>TeamList=new ArrayList<>();

    public bws() {
        g = DataManage.getGamedata();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("snow.admin")) {
                if (args.length >= 1) {
                    if(args[0].equalsIgnoreCase("help")){
                        p.sendMessage("§a-----------------§b[起床战争帮助]§a-----------------");
                        p.sendMessage("输入§e/bws create <Gameid> <队伍数量> <队伍人数> " + ChatColor.WHITE + "来创建游戏");
                        p.sendMessage("输入§e/bws setlobby" + ChatColor.WHITE + "来设置大厅位置（死亡后会传送到大厅）");
                        p.sendMessage("输入§e/bws createteam <TeamID> <Team名字> <队伍颜色（数字代码）>" + ChatColor.WHITE + "来创建队伍");
                        p.sendMessage("输入§e/bws createshop <TeamID> <U/C>" + ChatColor.WHITE + "来创建商店§6(U为升级商店 C为装备商店)");
                        p.sendMessage("输入§e/bws sethome <TeamID>" + ChatColor.WHITE + "来设置队伍出生点");
                        p.sendMessage("输入§e/bws setbed <TeamID>" + ChatColor.WHITE + "来设置队伍床");
                        p.sendMessage("输入§e/bws setIG <TeamID> <I/G>" + ChatColor.WHITE + "来设置队伍的资源点§6（I是铁，G是金）");
                        p.sendMessage("输入§e/bws setDE <D/E>" + ChatColor.WHITE + "来设置钻石§bD"+ChatColor.WHITE +"和绿宝石§aE");
                        p.sendMessage("输入§e/bws save" + ChatColor.WHITE + "来保存游戏设置§b");
                        p.sendMessage("§a-----------------§b[起床战争帮助]§a-----------------");
                    }else if(args[0].equalsIgnoreCase("create")){
                        if(args.length==4){
                            if(IscreateGame){
                                p.sendMessage("§a游戏§6"+args[1]+"§a已经被成功创建!队伍数量§6"+args[2]);
                            }else {
                                creategame(args[1],args[2],args[3]);
                                p.sendMessage("§a游戏§6"+args[1]+"§a成功创建!队伍数量§6"+args[2]);
                            }
                        }else {
                            p.sendMessage("输入§e/bws create <Gameid> <队伍数量> <队伍人数> " + ChatColor.WHITE + "来创建游戏");
                        }
                    }else if(args[0].equalsIgnoreCase("setlobby")){
                        if(args.length==1){
                            createlobby(p);
                            p.sendMessage("§a大厅已经成功设置！");
                        }else {
                            p.sendMessage("输入§e/bws setlobby" + ChatColor.WHITE + "来设置大厅位置（死亡后会传送到大厅）");
                        }
                    }else if(args[0].equalsIgnoreCase("createteam")){
                        if(args.length==4){
                            createTeam(args[1],args[3],args[2],p);
                        }else {
                            p.sendMessage("输入§e/bws createteam <TeamID> <Team名字> <队伍颜色（数字代码）>" + ChatColor.WHITE + "来创建队伍");
                        }

                    }else if(args[0].equalsIgnoreCase("createshop")){
                        if(args.length==3){
                            if(args[2].equalsIgnoreCase("U")){
                                createshop(args[1],p,"Ushop");
                            }else if(args[2].equalsIgnoreCase("C")){
                                createshop(args[1],p,"Cshop");
                            }
                        }else {
                            p.sendMessage("输入§e/bws createshop <TeamID> <U/C>" + ChatColor.WHITE + "来创建商店§6(U为升级商店 C为装备商店)");
                        }
                    }else if(args[0].equalsIgnoreCase("sethome")){
                        if(args.length==2){
                            setHomeLocation(args[1],p);
                        }else {
                            p.sendMessage("输入§e/bws sethome <TeamID>" + ChatColor.WHITE + "来设置队伍出生点");
                        }
                    }else if(args[0].equalsIgnoreCase("setbed")){
                        if(args.length==2){
                            setBedLocation(args[1],p);
                        }else {
                            p.sendMessage("输入§e/bws setbed <TeamID>" + ChatColor.WHITE + "来设置队伍床");
                        }
                    }else if(args[0].equalsIgnoreCase("setIG")){
                        if(args.length==3){
                            if(args[2].equalsIgnoreCase("i")){
                                setIron(args[1],p);
                            }else if(args[2].equalsIgnoreCase("g")){
                                setGold(args[1],p);
                            }
                        }else {
                            p.sendMessage("输入§e/bws setIG <TeamID> <I/G>" + ChatColor.WHITE + "来设置队伍的资源点§6（I是铁，G是金）");
                        }
                    }else if(args[0].equalsIgnoreCase("setDE")){
                        if(args.length==2){
                            if(args[1].equalsIgnoreCase("D")){
                                setDiamond(p);
                            }else if(args[1].equalsIgnoreCase("E")){
                                setE(p);
                            }
                        }else {
                            p.sendMessage("输入§e/bws setDE <D/E>" + ChatColor.WHITE + "来设置钻石§bD"+ChatColor.WHITE +"和绿宝石§aE");
                        }
                    } else if (args[0].equalsIgnoreCase("save")) {
                        if(args.length==1){
                            if(CheckGameC(p)){
                                saveGame();
                            }
                        }else {
                            p.sendMessage("输入§e/bws save" + ChatColor.WHITE + "来保存游戏设置§b");
                        }
                    }
                } else {
                    p.sendMessage("§e请输入§b/bws help§e获取帮助菜单");
                }
            } else {
                p.sendMessage("§c你没有权限执行！");
            }
        } else {
            System.out.println("§c该命令只能玩家输入");
        }
        return false;
    }

    //创建游戏
    private void creategame(String GameID, String Teamsize, String TeamMatesize) {
        g.createSection(GameID);
        game = g.getConfigurationSection(GameID);
        //队伍数据
        game.createSection("TeamData");
        TeamData = game.getConfigurationSection("TeamData");
        //队伍数量
        TeamData.set("TeamSize", Teamsize);
        //队伍人数
        TeamData.set("TeamMateSize", TeamMatesize);
        //队伍代码
        TeamData.createSection("TeamId");
        game.createSection("Location");
        Location = game.getConfigurationSection("Location");
        Location.createSection("TeamHome");
        Location.createSection("TeamBed");
        Location.createSection("Diamond");
        Location.createSection("Iron");
        Location.createSection("Gold");
        Location.createSection("Emerald");
        Location.createSection("shop");
        Location.createSection("common");
        IscreateGame=true;
    }

    //创建队伍
    private void createTeam(String TeamID, String TeamColour, String TeamName, Player p) {
        if(IscreateGame){
            if (Teamsize != Integer.valueOf(TeamData.getString("TeamSize"))) {
                ConfigurationSection T = TeamData.getConfigurationSection("TeamId");
                T.createSection(TeamID);
                ConfigurationSection A = T.getConfigurationSection(TeamID);
                A.set("TeamColour", "&" + TeamColour);
                A.set("TeamName", TeamName);
                Teamsize++;
                TeamList.add(TeamID);
                p.sendMessage("§a你成功创建了队伍：§6"+TeamName+"§bID:§e"+TeamID);
            } else {
                p.sendMessage("§c队伍已经满了，现有队伍数量：§e" + Teamsize);
                for (String id : TeamData.getConfigurationSection("TeamId").getKeys(false)) {
                    p.sendMessage("现在有的队伍有：" + ChatColor.translateAlternateColorCodes('&', TeamData.getConfigurationSection("TeamId").getConfigurationSection(id).getString("TeamColour")) + TeamData.getConfigurationSection("TeamId").getConfigurationSection(id).get("TeamName"));
                }
            }
        }else {
            p.sendMessage("§e请先创建游戏");
        }

    }

    //创建商店
    private void createshop(String Teamid, Player player, String ShopType) {
        if(IscreateGame){
            if (TeamData.getConfigurationSection("TeamId").getConfigurationSection(Teamid) != null) {
                Block block = player.getTargetBlock((Set<Material>) null,10);
                getL(Teamid,"shop").set(ShopType, block);
                TeamShopIsSet.put(Teamid, ShopType);
                player.sendMessage("§a你已经成功创建了"+ShopType);
            }else {
                player.sendMessage("§c未能找到该队伍");
            }
        }else {
            player.sendMessage("§e请先创建游戏");
        }
    }

    //设置大厅（死亡之后传送点）
    public void createlobby(Player p) {
        if(IscreateGame){
            Location.set("Lobby", p.getLocation());
            IsSetLobby = true;
        }else {
            p.sendMessage("§e请先创建游戏");
        }
    }

    //设置队伍床
    public void setBedLocation(String Teamid, Player player) {
        if(IscreateGame){
            if (TeamData.getConfigurationSection("TeamId").getConfigurationSection(Teamid) != null) {
                Block block = player.getTargetBlock((Set<Material>) null,10);
                if(block.getType().equals(Material.BED_BLOCK)){
                    getL(Teamid,"TeamBed").set(Teamid,block);
                    TeamBedIsSet.add(Teamid);
                    player.sendMessage("§e你已经成功设置了队伍"+Teamid+"的床");
                }else {
                    player.sendMessage("§c请对准床方块使用");
                }
            }else {
                player.sendMessage("§c请先创建队伍");
            }
        }else {
            player.sendMessage("§e请先创建游戏");
        }


    }
    //设置队伍的家
    public void setHomeLocation(String Teamid, Player player) {
        if(IscreateGame){
            if (TeamData.getConfigurationSection("TeamId").getConfigurationSection(Teamid) != null) {
                getL(Teamid,"TeamHome").set(Teamid,player.getLocation());
                TeamhomeIsSet.add(Teamid);
                player.sendMessage("§a你已经成功设置了队伍"+ Teamid +"的出生点");
            }else {
                player.sendMessage("§c请先创建队伍");
            }
        }else {
            player.sendMessage("§e请先创建游戏");
        }


    }
    //获取Lociton的配置文件
    private ConfigurationSection getL(String Teamid,String Path){
            if (Location.getConfigurationSection(Path).getConfigurationSection(Teamid) == null) {
                return Location.getConfigurationSection(Path).createSection(Teamid);
            } else {
                return Location.getConfigurationSection(Path).getConfigurationSection(Teamid);
            }

    }
    //设置队伍铁的资源点
    public void setIron(String Teamid,Player player){
        if(IscreateGame){
            if (TeamData.getConfigurationSection("TeamId").getConfigurationSection(Teamid) != null) {
                getL(Teamid,"TeamHome").set(Teamid,player.getLocation());
                Block block = player.getTargetBlock((Set<Material>) null,10);
                if(TeamIroncesIsSet.containsKey(Teamid)){
                    TeamIroncesIsSet.replace(Teamid,TeamIroncesIsSet.get(Teamid)+1);
                    getL(Teamid,"Iron").set("Iron"+TeamIroncesIsSet.get(Teamid)+1,block);
                }else {
                    TeamIroncesIsSet.put(Teamid,1);
                    getL(Teamid,"Iron").set("Iron1",block);
                }
                player.sendMessage("§a你已经成功创建队伍"+Teamid+"铁的资源的");
            }else {
                player.sendMessage("§c请先创建队伍");
            }
        }else {
            player.sendMessage("§e请先创建游戏");
        }

    }
    //设置队伍金的资源点
    public void setGold(String Teamid,Player player){
        if(IscreateGame){
            if (TeamData.getConfigurationSection("TeamId").getConfigurationSection(Teamid) != null) {
                getL(Teamid,"TeamHome").set(Teamid,player.getLocation());
                Block block = player.getTargetBlock((Set<Material>) null,10);
                if(TeamGoldcesIsSet.containsKey(Teamid)){
                    TeamGoldcesIsSet.replace(Teamid,TeamGoldcesIsSet.get(Teamid)+1);
                    getL(Teamid,"Gold").set("Gold"+TeamGoldcesIsSet.get(Teamid)+1,block);
                }else {
                    TeamGoldcesIsSet.put(Teamid,1);
                    getL(Teamid,"Gold").set("Gold1",block);
                }
                player.sendMessage("§a你已经成功创建队伍"+Teamid+"金的资源的");
            }else {
                player.sendMessage("§c请先创建队伍");
            }
        }else {
            player.sendMessage("§e请先创建游戏");
        }

    }
    //设置钻石的位置
    public void setDiamond(Player player){
        if(IscreateGame){
            Block block = player.getTargetBlock((Set<Material>) null,10);
            DiamondSize++;
            Location.getConfigurationSection("Diamond").set(String.valueOf(DiamondSize),block);
            player.sendMessage("§a钻石资源的设置成功，当前数量：§e"+DiamondSize);
        }else {
            player.sendMessage("§e请先创建游戏");
        }

    }
    //设置绿宝石的位置
    public void setE(Player player){
        if(IscreateGame){
            Block block = player.getTargetBlock((Set<Material>) null,10);
            EmeraldSize++;
            Location.getConfigurationSection("Emerald").set(String.valueOf(EmeraldSize),block);
            player.sendMessage("§a绿宝石资源的设置成功，当前数量：§e"+EmeraldSize);
        }else {
            player.sendMessage("§e请先创建游戏");
        }
    }
    //保存游戏
    public void saveGame(){
        DataManage.SaveGameSetting();
    }
    private boolean CheckGameC(Player player){
        if(!IscreateGame){
            player.sendMessage("§c未创建游戏");
            return false;
        }
        if(!IsSetLobby){
            player.sendMessage("§c未创建大厅");
            return false;
        }
        if(DiamondSize==0){
            player.sendMessage("§c未设置钻石资源点");
            return false;
        }
        if(EmeraldSize==0){
            player.sendMessage("§c未设置绿宝石资源点");
            return false;
        }

        if(TeamList.size()!=Integer.valueOf(TeamData.getString("TeamSize"))){
            player.sendMessage("§c队伍数量不够");
            return false;
        }
        if(TeamhomeIsSet.size()!=Integer.valueOf(TeamData.getString("TeamSize"))){
            player.sendMessage("§c有队伍的家没有设置");
            return false;
        }
        if(TeamBedIsSet.size()!=Integer.valueOf(TeamData.getString("TeamSize"))){
            player.sendMessage("§c有队伍的床没有设置");
            return false;
        }
        if(TeamShopIsSet.keySet().size()!=Integer.valueOf(TeamData.getString("TeamSize"))){
            player.sendMessage("§c有队伍的商店没有设置");
            return false;
        }
        if(TeamIroncesIsSet.keySet().size()!=Integer.valueOf(TeamData.getString("TeamSize"))){
            player.sendMessage("§c有队伍的铁没有设置");
            return false;
        }
        if(TeamGoldcesIsSet.keySet().size()!=Integer.valueOf(TeamData.getString("TeamSize"))){
            player.sendMessage("§c有队伍的黄金资源点没有设置");
            return false;
        }
        player.sendMessage("§a成功创建一个游戏！");
        return true;
    }

}
