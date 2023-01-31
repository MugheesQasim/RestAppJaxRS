package service;

import junit.framework.Assert;
import junit.framework.TestCase;

public class AuthenticationServiceTest extends TestCase {

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