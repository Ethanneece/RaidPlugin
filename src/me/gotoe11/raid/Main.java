package me.gotoe11.raid;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    @Override
    public void onEnable() 
    {
        new NationComand(this); 
    }
    
    @Override 
    public void onDisable()
    {
        
    }

}
