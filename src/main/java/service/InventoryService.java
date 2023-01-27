package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import domain.*;


public class InventoryService {
    public static InventoryService getInstance()
    {
        return new InventoryService();
    }

    public Inventory getInventoryById(String id) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM inventory JOIN itemcategory join itemlocation ON inventory.item_category_id=itemcategory.id AND inventory.item_location_id=itemlocation.id WHERE inventory.id =" + id);
        rs.next();
        Inventory inventory = new Inventory();
        inventory.setId(rs.getString(1));
        inventory.setItemName(rs.getString(2));
        inventory.setItemQuantity(rs.getInt(3));
        ItemCategory itemCategory = new ItemCategory(rs.getString(6),rs.getString(7));ItemLocation itemLocation = new ItemLocation(rs.getString(8),rs.getString(9));
        inventory.setItemCategoryId(itemCategory);
        inventory.setItemLocation(itemLocation);

        return inventory;
    }

    public List<Inventory> getInventoryAll() throws Exception
    {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
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

            return inventoryList;
    }

    public List<Inventory> getInventoryByCategory(String categoryId) throws Exception
    {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
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

            return inventoryList;
    }
    public List<Inventory> getInventoryByLocation(String locationId) throws Exception
    {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
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

            return inventoryList;
    }

    public List<Inventory> getInventoryByCategoryAndLocation(String categoryId,String locationId) throws Exception
    {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
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

            return inventoryList;
    }

    public Inventory addItem(Inventory inventory) throws Exception
    {
            System.out.println(inventory);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
            Statement st = con.createStatement();

            int rs = st.executeUpdate("insert into inventory (item_name, item_quantity, item_category_id, item_location_id)"
                            + " values (" +"'"+ inventory.getItemName()+ "'" +","+ "'" + inventory.getItemQuantity() + "'"
                            + "," + "'" + inventory.getItemCategory().getId() + "'" + "," + "'" + inventory.getItemLocation().getId() +"'" + ")");
            int rs2 = st.executeUpdate("insert into itemcategory (id, categoryname)" + "values(" + "'"+inventory.getItemCategory().getId()+"'" +
                    "," + "'" + inventory.getItemCategory().getCategoryName() + "'" + ")");
            int rs3 = st.executeUpdate("insert into itemlocation (id, locationname)" + "values(" + "'" + inventory.getItemLocation().getId() +"'" +
                    "," + "'" + inventory.getItemLocation().getLocationName() +"'" + ")");

            return inventory;
    }

    public Inventory updateInventory(Inventory inventory,String inventoryId) throws Exception
    {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
            Statement st = con.createStatement();

            st.executeQuery("UPDATE inventory SET item_name = " +inventory.getItemName() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE inventory SET item_quantity = " + inventory.getItemQuantity() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE inventory SET item_category_id = "+inventory.getItemCategory().getId() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE inventory SET item_location_id = " + inventory.getItemLocation().getId() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE itemCategory SET id = " + inventory.getItemCategory().getId() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE itemCategory SET category_name = " + inventory.getItemCategory().getCategoryName() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE itemlocation SET id = " + inventory.getItemLocation().getId() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE itemLocation SET location_name = " + inventory.getItemLocation().getLocationName() +"WHERE id=" + inventoryId);

            return inventory;
    }

    public void deleteInventory(String id) throws Exception
    {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "root");
            Statement st = con.createStatement();

            int rs = st.executeUpdate("DELETE FROM inventory WHERE id = " + id);
    }
}
