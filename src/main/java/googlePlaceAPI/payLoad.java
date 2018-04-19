package googlePlaceAPI;

public class payLoad {
    
    public static String postBodyData(){
        String b="{"+
                " \"location\": {"+
                "\"lat\": 31.7444,"+
                "\"lng\": 74.9163"+
              "},"+
              "\"accuracy\": 50,"+
              "\"name\": \"Sohian Kalan Village!\","+
              "\"phone_number\": \"(02) 9374 4000\","+
              "\"address\": \"VPO Sohian Kalan, Majitha, 14601, India\","+
              "\"types\": [\"atm\"],"+
              "\"language\": \"en\""+
        "}";
        return b;
    }

}
