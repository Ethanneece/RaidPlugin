package me.gotoe11.raid;

import org.bukkit.plugin.java.JavaPlugin;
import me.gotoe11.raid.commands.HelloCommand;

public class Main extends JavaPlugin {
    
    @Override
    public void onEnable() {
        new HelloCommand(this);
    }
    
}
