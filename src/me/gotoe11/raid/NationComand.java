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
            NationController nationController = plugin.getNations(); 
            
            return nationController.createNation(player, args[0]);
        }
        
        if (cmd.getName().equalsIgnoreCase("addPlayer"))
        {
            NationController nationController = plugin.getNations(); 
            
            Player applicant = plugin.getServer().getPlayer(args[0]);
            
            if (applicant == null)
            {
                sender.sendMessage("We can not find that player on the server.");
            }
            
            return nationController.addPlayer(player, applicant);
        }
        
        if (cmd.getName().equalsIgnoreCase("removePlayer"))
        {
            NationController nationController = plugin.getNations(); 
            
            Player applicant = plugin.getServer().getPlayer(args[0]);
            
            if (applicant == null)
            {
                player.sendMessage("We cannot find the player on the server.");
            }
            
            return nationController.addPlayer(player, applicant);
        }
        
        if (cmd.getName().equalsIgnoreCase("leaveNation"))
        {
            NationController nationController = plugin.getNations(); 
            
            return nationController.leaveNation(player);
        }
        
        if (cmd.getName().equalsIgnoreCase("transferLeadership"))
        {
            NationController nationController = plugin.getNations(); 
            
            Player applicant = plugin.getServer().getPlayer(args[0]);
            
            if (applicant == null)
            {
                player.sendMessage("We cannot find that player on the server");
            }
            
            return nationController.transferLeadership(player, applicant);
        }
        
        if (cmd.getName().equalsIgnoreCase("raid"))
        {
            NationController nationController = plugin.getNations(); 
            
            return nationController.raid(player);
        }
        
        return true;
    }

}
