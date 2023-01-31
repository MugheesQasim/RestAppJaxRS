package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
import domain.*;

public class InventoryService {
    public static InventoryService getInstance()
    {
        return new InventoryService();
    }

    public Inventory getInventoryById(String id) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = HikariCPService.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM inventory JOIN itemcategory join itemlocation ON inventory.item_category_id=itemcategory.id AND inventory.item_location_id=itemlocation.id WHERE inventory.id =" + id);

        rs.next();
        Inventory inventory = new Inventory();
        if(rs!=null)
        {
            inventory.setId(rs.getString(1));
            inventory.setItemName(rs.getString(2));
            inventory.setItemQuantity(rs.getInt(3));
            ItemCategory itemCategory = new ItemCategory(rs.getString(6),rs.getString(7));ItemLocation itemLocation = new ItemLocation(rs.getString(8),rs.getString(9));
            inventory.setItemCategoryId(itemCategory);
            inventory.setItemLocation(itemLocation);
        }

        con.close();
        return inventory;
    }
    public List<Inventory> getInventoryAll() throws Exception
    {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = HikariCPService.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from inventory JOIN itemcategory JOIN itemlocation ON inventory.item_category_id = itemcategory.id AND inventory.item_location_id = itemlocation.id");

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
            con.close();

            return inventoryList;
    }

    public List<Inventory> getInventoryByCategory(String categoryId) throws Exception
    {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = HikariCPService.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from inventory JOIN itemcategory JOIN itemlocation ON inventory.item_category_id = itemcategory.id AND inventory.item_location_id = itemlocation.id WHERE itemcategory.id=" + categoryId);

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
            con.close();

            Exception exception = new Exception();
            if(inventoryList.get(0)==null)
                throw exception;

            return inventoryList;
    }
    public List<Inventory> getInventoryByLocation(String locationId) throws Exception
    {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = HikariCPService.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from inventory JOIN itemcategory JOIN itemlocation ON inventory.item_category_id = itemcategory.id AND inventory.item_location_id = itemlocation.id WHERE itemlocation.id=" + locationId);

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
            con.close();

            Exception exception = new Exception();
            if(inventoryList.get(0)==null)
                throw exception;

            return inventoryList;
    }

    public List<Inventory> getInventoryByCategoryAndLocation(String categoryId,String locationId) throws Exception
    {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = HikariCPService.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from inventory JOIN itemcategory JOIN itemlocation ON inventory.item_category_id = itemcategory.id AND inventory.item_location_id = itemlocation.id WHERE itemcategory.id=" + categoryId +
                    " OR itemlocation.id = " + locationId);

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

            con.close();

            Exception exception = new Exception();
            if(inventoryList.get(0)==null)
                throw exception;

            return inventoryList;
    }

    public Inventory addItem(Inventory inventory) throws Exception
    {
            System.out.println(inventory);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = HikariCPService.getConnection();
            Statement st = con.createStatement();

            int rs = st.executeUpdate("insert into inventory (item_name, item_quantity, item_category_id, item_location_id)"
                            + " values (" +"'"+ inventory.getItemName()+ "'" +","+ "'" + inventory.getItemQuantity() + "'"
                            + "," + "'" + inventory.getItemCategory().getId() + "'" + "," + "'" + inventory.getItemLocation().getId() +"'" + ")");
            int rs2 = st.executeUpdate("insert into itemcategory (id, categoryname)" + "values(" + "'"+inventory.getItemCategory().getId()+"'" +
                    "," + "'" + inventory.getItemCategory().getCategoryName() + "'" + ")");
            int rs3 = st.executeUpdate("insert into itemlocation (id, locationname)" + "values(" + "'" + inventory.getItemLocation().getId() +"'" +
                    "," + "'" + inventory.getItemLocation().getLocationName() +"'" + ")");

            con.close();
            return inventory;
    }

    public Inventory updateInventory(Inventory inventory,String inventoryId) throws Exception
    {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = HikariCPService.getConnection();
            Statement st = con.createStatement();

            st.executeUpdate("UPDATE inventory SET item_name = " + "'"+inventory.getItemName()+"'" +" WHERE id=" + inventoryId);
            st.executeUpdate("UPDATE inventory SET item_quantity = " + "'" + inventory.getItemQuantity() + "'" + "WHERE id=" + inventoryId);
            st.executeUpdate("UPDATE inventory SET item_category_id = "+ "'"+inventory.getItemCategory().getId()+"'" +" WHERE id=" + inventoryId);
            st.executeUpdate("UPDATE inventory SET item_location_id = " + "'"+inventory.getItemLocation().getId()+"'" +" WHERE id=" + inventoryId);
            st.executeUpdate("UPDATE itemCategory SET id = " + "'" + inventory.getItemCategory().getId() + "'" +" WHERE id=" + inventoryId);
            st.executeUpdate("UPDATE itemCategory SET categoryname = " + "'" + inventory.getItemCategory().getCategoryName() +"'" +" WHERE id=" + inventoryId);
            st.executeUpdate("UPDATE itemlocation SET id = " + "'" + inventory.getItemLocation().getId() + "'" +" WHERE id=" + inventoryId);
            st.executeUpdate("UPDATE itemLocation SET locationname = " + "'" + inventory.getItemLocation().getLocationName()+"'" +" WHERE id=" + inventoryId);

            con.close();
            return inventory;
    }

    public void deleteInventory(String id) throws Exception
    {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = HikariCPService.getConnection();
            Statement st = con.createStatement();

            int rs = st.executeUpdate("DELETE FROM inventory WHERE id = " + id);

            Exception exception = new Exception();
            if(rs == 0)
                throw exception;

            con.close();
    }
    public boolean validateUser(String user) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = HikariCPService.getConnection();
        Statement st = con.createStatement();

        String[] authParts = user.split("\\s+");
        String authInfo = authParts[1];
        byte[] bytes  =  DatatypeConverter.parseBase64Binary(authInfo);
        String decodedAuth = new String(bytes);
        String[] userNameAndPassword = decodedAuth.split(":");

        AuthenticationService sj = new AuthenticationService();
        String hashedUsername = sj.getSHA256Hash(userNameAndPassword[0]);
        String hashedPassword = sj.getSHA256Hash(userNameAndPassword[1]);
        ResultSet rs = st.executeQuery("select * from users WHERE username=" + "'" + hashedUsername + "'" + " AND password=" + "'"+ hashedPassword+"'");
        rs.next();
        if(rs!=null)
            return true;
        else
            return false;
    }
}