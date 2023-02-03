package service;

import Config.HikariCPService;
import domain.sql.SqlQuery;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationService {

    public boolean validateUser(String user) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = HikariCPService.getInstance().getConnection();
            st = con.prepareStatement(SqlQuery.MYSQL_GET_USERNAME_AND_PASSWORD);

            String[] authParts = user.split("\\s+");
            String authInfo = authParts[1];
            byte[] bytes = DatatypeConverter.parseBase64Binary(authInfo);
            String decodedAuth = new String(bytes);
            String[] userNameAndPassword = decodedAuth.split(":");

            String hashedUsername = getSHA256Hash(userNameAndPassword[0]);
            String hashedPassword = getSHA256Hash(userNameAndPassword[1]);
            st.setString(1, hashedUsername);
            st.setString(2, hashedPassword);
            rs = st.executeQuery();

            return rs.next();
        }
        finally {
            if (st != null)
                st.close();
            if (rs != null)
                rs.close();
            if (con != null)
                con.close();
        }
    }

        public String getSHA256Hash(String data) {
            try
            {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
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
