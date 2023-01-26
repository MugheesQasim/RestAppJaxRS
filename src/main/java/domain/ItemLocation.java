package domain;

public class ItemLocation {
    private String id;
    private String locationName;

    public ItemLocation(String id,String locationName)
    {
        this.id = id;
        this.locationName = locationName;
    }
    public ItemLocation ()
    {

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
