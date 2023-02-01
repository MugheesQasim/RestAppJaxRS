package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import Config.Constants;
import Config.HikariCPService;
import domain.*;
import domain.sql.SqlQuery;

public class InventoryServiceImpl implements InventoryService{
    public static InventoryServiceImpl getInstance()
    {
        return new InventoryServiceImpl();
    }

    public Inventory getInventoryById(String id) throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Inventory inventory;
        try
        {
            con = HikariCPService.getInstance().getConnection();
            st = con.prepareStatement(SqlQuery.MYSQL_GET_BY_ID_INVENTORY);
            st.setString(1,id);
            rs = st.executeQuery();

            inventory = getInventory(rs);
        }
        finally {
            if(st!=null)
                st.close();
            if(rs!=null)
                rs.close();
            if(con!=null)
                con.close();
        }
        return inventory;
    }
    public List<Inventory> getInventoryAll() throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Inventory> inventoryList;
            try
            {
                con = HikariCPService.getInstance().getConnection();
                st = con.prepareStatement(SqlQuery.MYSQL_GET_ALL_INVENTORY);
                rs = st.executeQuery();

                inventoryList = getInventoryList(rs);
            }
            finally {
                if(st!=null)
                    st.close();
                if(rs!=null)
                    rs.close();
                if(con!=null)
                    con.close();
            }

            return inventoryList;
    }

    public List<Inventory> getInventoryByCategory(String categoryId) throws Exception
    {
            Connection con = null;
            PreparedStatement st = null;
            ResultSet rs = null;
            List<Inventory> inventoryList;

            try
            {
                con = HikariCPService.getInstance().getConnection();
                st = con.prepareStatement(SqlQuery.MYSQL_GET_BY_CATEGORY_INVENTORY);
                st.setString(1,categoryId);
                rs = st.executeQuery();

                inventoryList = getInventoryList(rs);
            }
            finally {
                if(st!=null)
                    st.close();
                if(rs!=null)
                    rs.close();
                if(con!=null)
                    con.close();
            }

            return inventoryList;
    }
    public List<Inventory> getInventoryByLocation(String locationId) throws Exception
    {
            Connection con = null;
            PreparedStatement st = null;
            ResultSet rs = null;
            List<Inventory> inventoryList;

            try
            {
                con = HikariCPService.getInstance().getConnection();
                st = con.prepareStatement(SqlQuery.MYSQL_GET_BY_LOCATION_INVENTORY);
                st.setString(1,locationId);
                rs = st.executeQuery();

                inventoryList = getInventoryList(rs);
            }
            finally {
                if(st!=null)
                    st.close();
                if(rs!=null)
                    rs.close();
                if(con!=null)
                    con.close();
            }

            return inventoryList;
    }

    public List<Inventory> getInventoryByCategoryAndLocation(String categoryId,String locationId) throws Exception
    {
            Connection con = null;
            PreparedStatement st = null;
            ResultSet rs = null;
            List<Inventory> inventoryList;

            try
            {
                con = HikariCPService.getInstance().getConnection();
                st = con.prepareStatement(SqlQuery.MYSQL_GET_BY_LOCATION_AND_CATEGORY_INVENTORY);
                st.setString(1,categoryId);
                st.setString(2,locationId);
                rs = st.executeQuery();

                inventoryList = getInventoryList(rs);
            }
            finally {
                if(st!=null)
                    st.close();
                if(rs!=null)
                    rs.close();
                if(con!=null)
                    con.close();
            }

            return inventoryList;
    }

    public Inventory addItem(Inventory inventory) throws Exception
    {
            Connection con = null;
            PreparedStatement st = null;
            try
            {
                con = HikariCPService.getInstance().getConnection();
                con.setAutoCommit(false);

                st = con.prepareStatement(SqlQuery.MYSQL_INSERT_QUERY_CATEGORY);
                st.setString(1,inventory.getItemCategory().getId());
                st.setString(2,inventory.getItemCategory().getCategoryName());
                st.executeUpdate();

                st = con.prepareStatement(SqlQuery.MYSQL_INSERT_QUERY_LOCATION);
                st.setString(1,inventory.getItemLocation().getId());
                st.setString(2,inventory.getItemLocation().getLocationName());
                st.executeUpdate();

                st = con.prepareStatement(SqlQuery.MYSQL_INSERT_QUERY_INVENTORY);
                st.setString(1,inventory.getId());
                st.setString(2,inventory.getItemName());
                st.setInt(3,inventory.getItemQuantity());
                st.setString(4,inventory.getItemCategory().getId());
                st.setString(5,inventory.getItemLocation().getId());
                st.executeUpdate();

                con.commit();
            }
            catch(Exception exc)
            {
                if(st!=null)
                    st.close();
                if(con!=null)
                    con.rollback();
            }
            finally {
                if(con!=null)
                    con.close();
            }

            return inventory;
    }

    public Inventory updateInventory(Inventory inventory,String inventoryId) throws Exception
    {
            Connection con = null;
            PreparedStatement st = null;
            try
            {
                con = HikariCPService.getInstance().getConnection();
                con.setAutoCommit(false);

                st = con.prepareStatement(SqlQuery.MYSQL_UPDATE_QUERY_CATEGORY);
                st.setString(1,inventory.getItemCategory().getId());
                st.setString(2,inventory.getItemCategory().getCategoryName());
                st.setString(3,inventory.getItemCategory().getId());
                st.executeUpdate();

                st = con.prepareStatement(SqlQuery.MYSQL_UPDATE_QUERY_LOCATION);
                st.setString(1,inventory.getItemLocation().getId());
                st.setString(2,inventory.getItemLocation().getLocationName());
                st.setString(3,inventory.getItemLocation().getId());
                st.executeUpdate();

                st = con.prepareStatement(SqlQuery.MYSQL_UPDATE_QUERY_INVENTORY);
                st.setString(1,inventory.getId());
                st.setString(2,inventory.getItemName());
                st.setInt(3,inventory.getItemQuantity());
                st.setString(4,inventory.getItemCategory().getId());
                st.setString(5,inventory.getItemLocation().getId());
                st.executeUpdate();

                con.commit();
            }
            catch (Exception exc)
            {
                if(st!=null)
                    st.close();
                if(con!=null)
                    con.rollback();
            }
            finally {
                if(con!=null)
                    con.close();
            }
            return inventory;
    }

    public void deleteInventory(String id) throws Exception
    {
            Connection con = null;
            Statement st = null;

            try
            {
                con = HikariCPService.getInstance().getConnection();
                st = con.createStatement();

                int rs = st.executeUpdate(SqlQuery.MYSQL_DELETE_QUERY_INVENTORY + id);
            }
            finally {
                if(st!=null)
                    st.close();
                if(con!=null)
                    con.close();
            }
    }
    public boolean validateUser(String user) throws Exception
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try
        {
            con = HikariCPService.getInstance().getConnection();
            st = con.prepareStatement(SqlQuery.MYSQL_GET_USERNAME_AND_PASSWORD);

            String[] authParts = user.split("\\s+");
            String authInfo = authParts[1];
            byte[] bytes  =  DatatypeConverter.parseBase64Binary(authInfo);
            String decodedAuth = new String(bytes);
            String[] userNameAndPassword = decodedAuth.split(":");

            AuthenticationService sj = new AuthenticationService();
            String hashedUsername = sj.getSHA256Hash(userNameAndPassword[0]);
            String hashedPassword = sj.getSHA256Hash(userNameAndPassword[1]);
            st.setString(1,hashedUsername);
            st.setString(2,hashedUsername);
            rs = st.executeQuery();

            if(rs.next())
                return true;
            else
                return false;
        }
        finally {
            if(st!=null)
                st.close();
            if(rs!=null)
                rs.close();
            if(con!=null)
                con.close();
        }
    }


    public Inventory getInventory(ResultSet rs) throws Exception
    {
        Inventory inventory = new Inventory();
        while(rs.next())
        {
            inventory.setId(rs.getString(1));
            inventory.setItemName(rs.getString(2));
            inventory.setItemQuantity(rs.getInt(3));

            ItemCategory itemCategory = new ItemCategory(rs.getString(6),rs.getString(7));
            ItemLocation itemLocation = new ItemLocation(rs.getString(8),rs.getString(9));
            inventory.setItemCategoryId(itemCategory);
            inventory.setItemLocation(itemLocation);
        }
        return inventory;
    }

    public List<Inventory> getInventoryList(ResultSet rs) throws SQLException
    {
        List<Inventory> inventoryList = new ArrayList<>();

        while(rs.next())
        {
            Inventory inventory = new Inventory();

            inventory.setId(rs.getString(1));
            inventory.setItemName(rs.getString(2));
            inventory.setItemQuantity(rs.getInt(3));

            ItemCategory itemCategory = new ItemCategory(rs.getString(6),rs.getString(7));
            ItemLocation itemLocation = new ItemLocation(rs.getString(8),rs.getString(9));
            inventory.setItemCategoryId(itemCategory);
            inventory.setItemLocation(itemLocation);

            inventoryList.add(inventory);
        }
        return inventoryList;
    }

}