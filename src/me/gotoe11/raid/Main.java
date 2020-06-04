package me.gotoe11.raid;

import java.io.IOException;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    private NationController nations; 
    
    @Override
    public void onEnable() 
    {
        new NationComand(this); 
        try {
            nations = new NationController(this);
        }
        catch (IOException e) {
            e.printStackTrace(); 
        } 
    }
    
    @Override 
    public void onDisable()
    {
        try {
            nations.saveNation();
        }
        catch (IOException e) {
            this.getLogger().info("saving Nations went wrong");
        } 
    }
    
    public NationController getNations()
    {
        return nations; 
    }

}
