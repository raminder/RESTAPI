package googlePlaceAPI;

public class resources1 {
    
    public static String searchPlace(){
    
        String getplace= "/maps/api/place/nearbysearch/json";
        return getplace;
    }
    
    public static String addplace()
    {
        String addplace= "/maps/api/place/add/json";
        return addplace;
    }

    public static String deletePlace()
    {
        String deleteplace= "/maps/api/place/delete/json";
        return deleteplace;
    }
}
