package service;

import junit.framework.TestCase;
import junit.framework.TestResult;

import java.util.concurrent.ExecutionException;

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