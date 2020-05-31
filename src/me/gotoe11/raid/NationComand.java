package me.gotoe11.raid;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NationComand implements CommandExecutor {
    
    private Main plugin; 
    
    public NationComand(Main plugin)
    {
        this.plugin = plugin; 
        
        plugin.getCommand("createNation").setExecutor(this);
        plugin.getCommand("addPlayer").setExecutor(this);
        plugin.getCommand("removePlayer").setExecutor(this);
        plugin.getCommand("leaveNation").setExecutor(this);
        plugin.getCommand("transferLeadership").setExecutor(this);
        plugin.getCommand("raid").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if (!(sender instanceof Player))
        {
            sender.sendMessage("only players should be using these commands!");
        }
        
        Player player = (Player) sender; 
        
        if (cmd.getName().equalsIgnoreCase("createNation"))
        {
            
            new Nation(player, args[0]);
        }
        
        return true;
    }

}
