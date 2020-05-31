package me.gotoe11.raid;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;

/**
 * 
 * @author Ethan Neece
 * @version 5-31-2020
 */
public class Nation {
    
    //5 players are considered a nation. 
    private static final int REAL_NATION  = 5; 
    
    //50 players are the maximum allowed number of players in a Nation. 
    private static final int PLAYER_MAX = 50; 
    
    private List<Player> players; 
    private String name; 
    private Player leader; 
    
    /**
     * Creates an object of Nation to be used for raiding. 
     * 
     * @param leader This Person is the person who first sets up the Nation and is put in charge. 
     * @param name what the Nation is called. 
     */
    public Nation(Player leader, String name)
    {
        players = new ArrayList<Player>();
        this.leader = leader; 
        this.name = name; 
        
        leader.sendMessage("Nation: " + name + " successfully created!");
    }
    
    /**
     *  
     * @return the name of the nation. 
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * 
     * @return the list of players in the Nation. 
     */
    public List<Player> getPlayers()
    {
        return players; 
    }
    
    /**
     * Removes player from this Nation. 
     *  The person calling this needs to be the leader of the Nation or will not work. 
     *  
     * @param caller this is the player who called the command. 
     * @param removed this is the player who is getting removed. 
     * @return true if the removal was sucessful and false otherwise. 
     */
    public boolean removePlayer(Player caller, Player removed)
    {
        
        if (!isLeader(caller))
        {
            caller.sendMessage("Only the leader of the Nation may remove someone!");
            return false; 
        }
        
        if (caller.equals(removed))
        {
            return leaveNation(caller);
        }
        
        if (!players.contains(removed))
        {
            caller.sendMessage("Person was not found in Nation.");
        }
        else
        {
            if (!players.remove(removed))
            {
                System.out.println("Nation: " + name + " could not remove" + removed.getName());
            }
            caller.sendMessage("Removal was sucessful");
        }
        return true; 
    }
    
    /**
     * This permits a player current in the Nation to leave it. 
     *  Does not let the leader leave without transferring permissions unless he is 
     *   the only one in the Nation. 
     * @param caller the Person who called this method. 
     * @return true if the player successfully left the nation and false otherwise. 
     */
    public boolean leaveNation(Player caller)
    {
        if (players.size() == 1)
        {
            if (players.remove(caller))
            {
                System.out.println("Removal of " + caller.getName() + " from " + name + " went wrong");
            }
            else
            {
                caller.sendMessage("removal Sucessful");
            }
        }
        
        if (isLeader(caller))
        {
            caller.sendMessage("You must transfer ownership before leaving the Nation.");
            return false; 
        }
        
        if (!players.remove(caller))
        {
            System.out.println("Removal of " + caller.getName() + " from " + name + " went wrong");
            return false; 
        }
        
        return true; 
    }
    
    /**
     * adds a Player to the Nation. 
     *  The leader of the Nation needs to call this or it will not work. 
     *  
     * @param caller The player who called this command. 
     * @param added The player who is being added to the Nation. 
     * @return true if the player is added and false otherwise. 
     */
    public boolean addPlayer(Player caller, Player added)
    {
        if (!isLeader(caller))
        {
            caller.sendMessage("Only a leader of the Nation may add someone!");
            return false; 
        }
        
        if (isMaxSize())
        {
            caller.sendMessage("You guys have the maximum amount of players, to add someone else to the "
                + "nation you must make space");
            
            return false; 
        }
        
        if (players.contains(added))
        {
            caller.sendMessage("Player: " + added.getName() + "is already in the Nation");
        }
        
        if (!players.add(added))
        {
            System.out.println("Nation:" + name + " could not add " + added.getName());
            return false; 
        }
        
        caller.sendMessage("Player: " + added.getName() + " was added successfully");
        return true; 
    }
    
    /**
     * Transfers leadership from one player to another. 
     *  Leader must call this method for it to work. 
     *  Leader must transfer to another player in the Nation. 
     *  
     * @param caller is the player who called this command. 
     * @param player is the player that ownership is being transferred to. 
     * @return true if leadership is transferred successfully and false otherwise. 
     */
    public boolean transferLeadership(Player caller, Player player)
    {
        if (!isLeader(caller))
        {
            caller.sendMessage("You can't transfer what you don't own.");
            return false; 
        }
        
        if (players.contains(player))
        {
            leader = player; 
            caller.sendMessage("You have succesful transfered leadership.");
            return true;
        }
        
        caller.sendMessage("To transfer ownership that player must be in your Nation");
        return false; 
    }
    
    public boolean raid(Player caller)
    {
        if (!isLeader(caller))
        {
            caller.sendMessage("Only the leader can create raids");
        }
        
        //TODO: Check if the caller is on the right block. 
        //TODO: Send server wide message. 
        //TODO: Start timer for when Raiding. 
        //TODO: Disable homes when timer finishes.
        //TODO: Create copy of space to replicate. 
        
        return true; 
    }
    
    private boolean isMaxSize()
    {
        return players.size() == PLAYER_MAX; 
    }
    
    private boolean isLeader(Player player) 
    {
        return player.equals(leader);
    }
    
    private boolean isValidNation()
    {
        return players.size() == REAL_NATION; 
    }
}
