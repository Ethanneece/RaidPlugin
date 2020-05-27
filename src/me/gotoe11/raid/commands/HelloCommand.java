package me.gotoe11.raid.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.gotoe11.raid.Main; 

public class HelloCommand implements CommandExecutor {
    
    private Main plugin; 
    
    public HelloCommand(Main plugin)
    {
        this.plugin = plugin;
        plugin.getCommand("hello").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may execute this command!");
            return true; 
        }
        
        Player player = (Player) sender; 
        
        if (player.hasPermission("hello.use")) {
            player.sendMessage("hi!");
            return true; 
        }
        
        return false;
    }

}
