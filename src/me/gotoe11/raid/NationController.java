package me.gotoe11.raid;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.entity.Player;

/**
 * 
 * @author Ethan Neece
 * @version: 5/31/2020
 */
public class NationController {
    
    private static final String FILE_DIRECTORY = "RaidPlugin";
    private static final String NATION_FILE = "nations.txt";

    private ArrayList<Nation> nations; 
    private HashMap<Player, Nation> nationMap; 
    
    public NationController()
    {
        nationMap = new HashMap<Player, Nation>(); 
        nations = new ArrayList<Nation>(); 
        
        File directory = new File(FILE_DIRECTORY);
        directory.mkdir(); 
        
    }
    
    public boolean createNation(Player caller, String nationName)
    {
        Nation nation = nationMap.get(caller);
        
        if (nation != null)
        {
            caller.sendMessage("You can't call this nation if you are already part of a nation");
            return false; 
        }
        
        Nation newNation = new Nation(caller, nationName);
        nations.add(newNation);
        nationMap.put(caller, newNation);
        return true; 
    }
    
    public boolean raid(Player caller)
    {
        Nation nation = nationMap.get(caller);
        
        if (nation == null)
        {
            caller.sendMessage("Only players part of a Nation can use this");
            return false; 
        }
        
        return nation.raid(caller);
    }
    
    public boolean transferLeadership(Player caller, Player applicant)
    {
        Nation nation = nationMap.get(caller);
        
        if (nation == null)
        {
            caller.sendMessage("Only players part of a nation can use this"); 
        }
        
        return nation.transferLeadership(caller, applicant);
    }
    
    public boolean removePlayer(Player caller, Player applicant)
    {
        Nation nation = nationMap.get(caller);
        
        if (nation == null)
        {
            caller.sendMessage("Only players part of a nation can use this");
            return false; 
        }
        
        boolean removed = nation.removePlayer(caller, applicant);
        if (removed)
        {
            nationMap.remove(applicant);
        }
        
        return removed; 
    }
    
    public boolean leaveNation(Player caller)
    {
        Nation nation = nationMap.get(caller);
        
        if (nation == null)
        {
            caller.sendMessage("You must be in a Nation to leave it.");
        }
        
        return nation.leaveNation(caller);
    }
    
    public boolean addPlayer(Player caller, Player applicant)
    {
        Nation nation = nationMap.get(applicant);
        
        if (nation != null)
        {
            caller.sendMessage("Player: " + applicant.getName() + " is a part of nation " + nation.getName());
            return false; 
        }
        
        Nation nationCaller = nationMap.get(caller);
        
        boolean added = nationCaller.addPlayer(caller, applicant);
        
        if(added)
        {
            nationMap.put(applicant, nationCaller);
        }
        
        return true; 
    }
    
    public boolean saveNation() throws IOException
    {
        File saveFile = new File(FILE_DIRECTORY + NATION_FILE);
        BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
        
        writer.write("Nations:\n");
        for (Nation nation : nations)
        {
            writer.write("\t" + nation.getName());
            
            for (Player player : nation.getPlayers())
            {
                writer.write("\t\t" + player.getName());
            }
        }
        
        writer.close(); 
        
        return true; 
    }
}
