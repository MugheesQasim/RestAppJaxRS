package service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class AuthenticationService {

        public String getSHA256Hash(String data) {
            try
            {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(data.getBytes("UTF-8"));
                return bytesToHex(hash); // make it printable
            }
            catch(Exception exc)
            {
                return null;
            }
        }

        private String  bytesToHex(byte[] hash) {
            return DatatypeConverter.printHexBinary(hash);
        }
}
