package service;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

public class AuthenticationServiceTest{

    @Test
    public void testAlgorithm()
    {
        try
        {
            AuthenticationService auth = new AuthenticationService();
            auth.getSHA256Hash("");
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }

    }

}