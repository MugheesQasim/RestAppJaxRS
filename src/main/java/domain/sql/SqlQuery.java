package domain.sql;

public abstract class SqlQuery {
    public final static String MYSQL_INSERT_QUERY_INVENTORY = "INSERT INTO inventory(id,item_name,item_quantity," +
            "item_category_id,item_location_id) values (?,?,?,?,?)";

    public final static String MYSQL_INSERT_QUERY_CATEGORY = "INSERT INTO item_category(id,category_name) values (?,?)";

    public final static String MYSQL_INSERT_QUERY_LOCATION  = "INSERT INTO item_location(id,location_name) values (?,?)";

    public final static String MYSQL_UPDATE_QUERY_CATEGORY = "UPDATE item_category SET category_name = ? WHERE id = ?";

    public final static String MYSQL_UPDATE_QUERY_LOCATION = "UPDATE item_location SET location_name = ? WHERE id = ?";

    public final static String MYSQL_UPDATE_QUERY_INVENTORY = "UPDATE inventory SET item_name = ?, item_quantity = ? "+
                                    ", item_category_id = ?, item_location_id = ? WHERE id = ?";

    public final static String MYSQL_DELETE_QUERY_INVENTORY = "DELETE FROM inventory WHERE id = ";

    public final static String MYSQL_GET_ALL_INVENTORY = "select * from inventory JOIN item_category JOIN item_location ON inventory.item_category_id = item_category.id AND inventory.item_location_id = item_location.id";

    public final static String MYSQL_GET_BY_ID_INVENTORY = "SELECT * FROM inventory JOIN item_category join item_location ON inventory.item_category_id=item_category.id AND inventory.item_location_id=item_location.id WHERE inventory.id = ?";

    public final static String MYSQL_GET_BY_CATEGORY_INVENTORY = "select * from inventory JOIN item_category JOIN item_location \" +\n" +
            "                                         \"ON inventory.item_category_id = item_category.id AND inventory.item_location_id = item_location.id \" +\n" +
            "                                         \"WHERE item_category.id= ?";

    public final static String MYSQL_GET_BY_LOCATION_INVENTORY = "select * from inventory JOIN item_category JOIN item_location ON inventory.item_category_id = item_category.id AND inventory.item_location_id = item_location.id WHERE item_location.id= ?";

    public final static String MYSQL_GET_BY_LOCATION_AND_CATEGORY_INVENTORY = "select * from inventory JOIN item_category JOIN item_location ON inventory.item_category_id = item_category.id AND inventory.item_location_id = item_location.id " +
            "WHERE item_category.id= ? OR item_location.id = ?";

    public final static String MYSQL_GET_USERNAME_AND_PASSWORD = "select * from users WHERE username= ? AND password = ?";
}
