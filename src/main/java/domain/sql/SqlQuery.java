package domain.sql;

public abstract class SqlQuery {
    public final static String MYSQL_INSERT_QUERY_INVENTORY = "INSERT INTO inventory(id,item_name,item_quantity," +
            "item_category_id,item_location_id) values (?,?,?,?,?)";

    public final static String MYSQL_INSERT_QUERY_CATEGORY = "INSERT INTO itemcategory(id,categoryname) values (?,?)";

    public final static String MYSQL_INSERT_QUERY_LOCATION  = "INSERT INTO itemlocation(id,locaitonname) values (?,?)";

    public final static String MYSQL_UPDATE_QUERY_CATEGORY = "UPDATE itemcategory SET id = ?, categoryname = ? WHERE id = ?";

    public final static String MYSQL_UPDATE_QUERY_LOCATION = "UPDATE itemlocation SET id = ?, locationname = ? WHERE id = ?";

    public final static String MYSQL_UPDATE_QUERY_INVENTORY = "UPDATE inventory SET id = ?, item_name = ?, item_quantity = ? "+
                                    ", item_category_id = ?, item_location_id = ? WHERE id = ?";

    public final static String MYSQL_DELETE_QUERY_INVENTORY = "DELETE FROM inventory WHERE id = ";

    public final static String MYSQL_GET_ALL_INVENTORY = "select * from inventory JOIN itemcategory JOIN itemlocation ON inventory.item_category_id = itemcategory.id AND inventory.item_location_id = itemlocation.id";

    public final static String MYSQL_GET_BY_ID_INVENTORY = "SELECT * FROM inventory JOIN itemcategory join itemlocation ON inventory.item_category_id=itemcategory.id AND inventory.item_location_id=itemlocation.id WHERE inventory.id = ?";

    public final static String MYSQL_GET_BY_CATEGORY_INVENTORY = "select * from inventory JOIN itemcategory JOIN itemlocation \" +\n" +
            "                                         \"ON inventory.item_category_id = itemcategory.id AND inventory.item_location_id = itemlocation.id \" +\n" +
            "                                         \"WHERE itemcategory.id= ?";

    public final static String MYSQL_GET_BY_LOCATION_INVENTORY = "select * from inventory JOIN itemcategory JOIN itemlocation ON inventory.item_category_id = itemcategory.id AND inventory.item_location_id = itemlocation.id WHERE itemlocation.id= ?";

    public final static String MYSQL_GET_BY_LOCATION_AND_CATEGORY_INVENTORY = "select * from inventory JOIN itemcategory JOIN itemlocation ON inventory.item_category_id = itemcategory.id AND inventory.item_location_id = itemlocation.id " +
            "WHERE itemcategory.id= ? OR itemlocation.id = ?";

    public final static String MYSQL_GET_USERNAME_AND_PASSWORD = "select * from users WHERE username= ? AND password = ?";
}
