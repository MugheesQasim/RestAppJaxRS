package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Config.HikariCPService;
import domain.*;
import domain.sql.SqlQuery;


public class InventoryServiceImpl implements InventoryService{
    public static InventoryServiceImpl getInstance()
    {
        return new InventoryServiceImpl();
    }

    public Inventory getInventoryById(String id) throws SQLException {
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

            inventory = getInventoryId(rs);
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

    public List<Inventory> getInventory(String category,String location) throws SQLException
    {
        List<Inventory> inventoryList;

        if(category==null && location==null)
            inventoryList = getInventoryAll();
        else if(category!=null && location==null)
            inventoryList = getInventoryByCategory(category);
        else if(category == null)
            inventoryList = getInventoryByLocation(location);
        else
            inventoryList = getInventoryByCategoryAndLocation(category,location);

        return inventoryList;
    }

    public List<Inventory> getInventoryAll() throws SQLException
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

    public List<Inventory> getInventoryByCategory(String categoryId) throws SQLException
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
    public List<Inventory> getInventoryByLocation(String locationId) throws SQLException
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

    public List<Inventory> getInventoryByCategoryAndLocation(String categoryId,String locationId) throws SQLException
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

    public Inventory addItem(Inventory inventory) throws SQLException
    {
            Connection con = null;
            PreparedStatement st = null;
            try
            {
                con = HikariCPService.getInstance().getConnection();
                con.setAutoCommit(false);

                st = con.prepareStatement(SqlQuery.MYSQL_INSERT_QUERY_CATEGORY);
                st.setString(1,inventory.getItem_category().getId());
                st.setString(2,inventory.getItem_category().getcategory_name());
                st.executeUpdate();

                st = con.prepareStatement(SqlQuery.MYSQL_INSERT_QUERY_LOCATION);
                st.setString(1,inventory.getItem_location().getId());
                st.setString(2,inventory.getItem_location().getLocation_name());
                st.executeUpdate();

                st = con.prepareStatement(SqlQuery.MYSQL_INSERT_QUERY_INVENTORY);
                st.setString(1,inventory.getId());
                st.setString(2,inventory.getItem_name());
                st.setInt(3,inventory.getItem_quantity());
                st.setString(4,inventory.getItem_category().getId());
                st.setString(5,inventory.getItem_location().getId());
                st.executeUpdate();

                con.commit();
            }
            catch(Exception exc)
            {

                if(con!=null)
                    con.rollback();
                throw exc;

            }
            finally {
                if(st!=null)
                    st.close();
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
                st.setString(1,inventory.getItem_category().getcategory_name());
                st.setString(2,inventory.getItem_category().getId());
                st.executeUpdate();

                st = con.prepareStatement(SqlQuery.MYSQL_UPDATE_QUERY_LOCATION);
                st.setString(1,inventory.getItem_location().getLocation_name());
                st.setString(2,inventory.getItem_location().getId());
                st.executeUpdate();

                st = con.prepareStatement(SqlQuery.MYSQL_UPDATE_QUERY_INVENTORY);
                st.setString(1,inventory.getItem_name());
                st.setInt(2,inventory.getItem_quantity());
                st.setString(3,inventory.getItem_category().getId());
                st.setString(4,inventory.getItem_location().getId());
                st.setString(5,inventoryId);
                st.executeUpdate();

                con.commit();
            }
            catch (Exception exc)
            {
                if(con!=null)
                    con.rollback();
                throw exc;
            }
            finally {
                if(st!=null)
                    st.close();
                if(con!=null)
                    con.close();
            }
            return inventory;
    }

    public void deleteInventory(String id) throws Exception {
        Connection con = null;
        Statement st = null;

        try {
            con = HikariCPService.getInstance().getConnection();
            st = con.createStatement();

            st.executeUpdate(SqlQuery.MYSQL_DELETE_QUERY_INVENTORY + id);
        } finally {
            if (st != null)
                st.close();
            if (con != null)
                con.close();
        }
    }


    public Inventory getInventoryId(ResultSet rs) throws SQLException {
        Inventory inventory = null;
        while(rs.next())
        {
            inventory = new Inventory();
            inventory.setId(rs.getString(1));
            inventory.setItem_name(rs.getString(2));
            inventory.setItem_quantity(rs.getInt(3));

            ItemCategory itemCategory = new ItemCategory(rs.getString(6),rs.getString(7));
            ItemLocation itemLocation = new ItemLocation(rs.getString(8),rs.getString(9));
            inventory.setItemCategoryId(itemCategory);
            inventory.setItem_location(itemLocation);
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
            inventory.setItem_name(rs.getString(2));
            inventory.setItem_quantity(rs.getInt(3));

            ItemCategory itemCategory = new ItemCategory(rs.getString(6),rs.getString(7));
            ItemLocation itemLocation = new ItemLocation(rs.getString(8),rs.getString(9));
            inventory.setItemCategoryId(itemCategory);
            inventory.setItem_location(itemLocation);

            inventoryList.add(inventory);
        }
        return inventoryList;
    }

}