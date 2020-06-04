package me.gotoe11.raid;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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
    
    private Main plugin; 
    
    public NationController(Main main) throws IOException
    {
        plugin = main; 
        nationMap = new HashMap<Player, Nation>(); 
        nations = new ArrayList<Nation>(); 
        
        File directory = new File(FILE_DIRECTORY);
        if (!directory.exists())
        {
            directory.mkdir();
        }
        
        File saveData = new File(FILE_DIRECTORY + NATION_FILE);
        if (!saveData.exists())
        {
            saveData.createNewFile();

        }
        
        else
        {
            Scanner reader = new Scanner(saveData);
            
            while(reader.hasNextLine())
            {
                String nationString = reader.nextLine(); 
                String[] nationPlayer = nationString.split(" ");
                Player leader = plugin.getServer().getPlayer(nationPlayer[1]);
                Nation nation = new Nation(leader, nationPlayer[0].substring(0, nationPlayer[0].indexOf(":")));
                
                for (int i = 2; i < nationPlayer.length; i++)
                {
                    nation.addPlayer(leader, plugin.getServer().getPlayer(nationPlayer[i]));
                }
            }
            
            reader.close(); 
        }
        
    }
    
    public boolean createNation(Player caller, String nationName)
    {
        Nation nation = findNation(caller);
        
        if (nation != null)
        {
            caller.sendMessage("You can't call this nation if you are already part of a faction");
            return false; 
        }
        
        Nation newNation = new Nation(caller, nationName);
        nations.add(newNation);
        nationMap.put(caller, newNation);
        return true; 
    }
    
    public boolean raid(Player caller)
    {
        Nation nation = findNation(caller);
        
        if (nation == null)
        {
            caller.sendMessage("Only players part of a faction can use this");
            return false; 
        }
        
        return nation.raid(caller);
    }
    
    public boolean transferLeadership(Player caller, Player applicant)
    {
        Nation nation = findNation(caller);
        
        if (nation == null)
        {
            caller.sendMessage("Only players part of a faction can use this"); 
        }
        
        return nation.transferLeadership(caller, applicant);
    }
    
    public boolean setHome(Player caller)
    {
        Nation nation = findNation(caller);
        
        if (nation == null)
        {
            caller.sendMessage("You have to be part of a nation to set a home.");
            return false;
        }
        
        return nation.setNationHome(caller);
    }
    
    public boolean nationHome(Player caller)
    {
        Nation nation = findNation(caller);
        
        if (nation == null)
        {
            caller.sendMessage("You have to be part of a nation to set a home.");
        }
        
        return nation.nationHome(caller);
    }
    
    public boolean removePlayer(Player caller, Player applicant)
    {
        Nation nation = findNation(caller);
        
        if (nation == null)
        {
            caller.sendMessage("Only players part of a faction can use this");
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
        Nation nation = findNation(caller);
        
        if (nation == null)
        {
            caller.sendMessage("You must be in a faction to leave it.");
        }
        
        return nation.leaveNation(caller);
    }
    
    public boolean addPlayer(Player caller, Player applicant)
    {
        Nation nation = findNation(applicant);
        
        if (nation != null)
        {
            caller.sendMessage("Player: " + applicant.getName() + " is a part of faction " + nation.getName());
            return false; 
        }
        
        Nation nationCaller = nationMap.get(caller);
        
        boolean added = nationCaller.addPlayer(caller, applicant);
        
        if (added)
        {
            nationMap.put(applicant, nationCaller);
            return true; 
        }
        
        return false; 
    }
    
    public boolean acceptNation(Player caller, String nationName)
    {
        Nation nation = findNation(nationName);
        
        if (nation == null)
        {
            caller.sendMessage("Nation " + nationName + " could not be found");
            return false; 
        }
        return nation.accept(caller);
    }
    
    private Nation findNation(String nationName)
    {
        for (Nation nation : nations)
        {
            if (nation.getName().equalsIgnoreCase(nationName))
            {
                return nation; 
            }
        }
        
        return null; 
    }
    
    private Nation findNation(Player caller)
    {
        return nationMap.get(caller);
    }
    
    public boolean saveNation() throws IOException
    {
        File saveFile = new File(FILE_DIRECTORY + NATION_FILE);
        BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
        
        writer.write("Nations:\n");
        for (Nation nation : nations)
        {
            writer.write(nation.getName() + ": ");
            
            for (Player player : nation.getPlayers())
            {
                writer.write(player.getName() + " ");
            }
        }
        
        writer.close(); 
        
        return true; 
    }
}
