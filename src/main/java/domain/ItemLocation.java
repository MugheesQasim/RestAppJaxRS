package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemLocation {
    @JsonProperty("id")
    private String id;
    @JsonProperty("location_name")
    private String location_name;

    public ItemLocation(String id,String location_name)
    {
        this.id = id;
        this.location_name = location_name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }
}
