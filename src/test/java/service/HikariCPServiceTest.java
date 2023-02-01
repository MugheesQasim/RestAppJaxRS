package service;

import Config.HikariCPService;
import junit.framework.TestCase;

public class HikariCPServiceTest extends TestCase {

    public void testGetConnection()
    {
        try{
            HikariCPService.getConnection();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }
}