package me.gotoe11.test;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import me.gotoe11.raid.Main;
import me.gotoe11.raid.NationController;

public class NationControllerTest {

    @Test
    public void testSave()
    {
        try {
            new NationController(new Main());
        }
        catch (IOException e) {
            assertNotNull(e);
        } 
        
        File directory = new File("RaidPlugin");
        
        System.out.println(directory.getAbsolutePath());
        assertTrue("Failed to create directory.", directory.exists());
    }
}
