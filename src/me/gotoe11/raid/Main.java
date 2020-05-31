package me.gotoe11.raid;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    private NationController nations; 
    
    @Override
    public void onEnable() 
    {
        new NationComand(this); 
        nations = new NationController(); 
    }
    
    @Override 
    public void onDisable()
    {
        
    }
    
    public NationController getNations()
    {
        return nations; 
    }

}
