package jiraAPI;

public class resources1 {
    
 
    public static String jiraCommonRes(){
    
        String jiraCommonResources= "/rest/api/2/issue";
        return jiraCommonResources;
    }
    
    public static String getSession(){
        
        String getSession= "/rest/auth/1/session";
        return getSession;
    }
}