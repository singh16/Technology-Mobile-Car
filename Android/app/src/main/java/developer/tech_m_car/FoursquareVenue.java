package developer.tech_m_car;

/**
 * Created by Jaspreet on 9/9/2015.
 */
public class FoursquareVenue {
    private String name;
    private String city;
    private String lat;
    private String lng;

    private String category;

    public FoursquareVenue() {
        this.name = "";
        this.city = "";
        this.lat ="";
        this.lng = "";
        this.setCategory("");
    }

    public String getCity() {
        if (city.length() > 0) {
            return city;
        }
        return city;
    }
    public String  getlat() {
        if (lat.length() > 0) {
            return lat;
        }
        return lat;
    }
    public String  getlong() {
        if (lng.length() > 0) {
            return lng;
        }
        return lng;
    }

    public void setCity(String city) {
        if (city != null) {
            this.city = city.replaceAll("\\(", "").replaceAll("\\)", "");
            ;
        }
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setlat(String lat) {
        this.lat=lat;
    }
    public void setlong(String lng) {
        this.lng=lng;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}

