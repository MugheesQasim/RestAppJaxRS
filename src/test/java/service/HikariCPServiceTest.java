package service;

import Config.HikariCPService;
import junit.framework.TestCase;
import org.junit.Test;

public class HikariCPServiceTest {

    @Test
    public void testGetConnection()
    {
        try{
            HikariCPService.getInstance().getConnection();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }
}