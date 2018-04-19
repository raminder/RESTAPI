package jiraAPI;

public class payLoad {
    
public static String postBodyCreateIssue(){
        String postCreateIssue="{"+ 
    "\"fields\": {"+ 
    "\"project\":{"+ 
    "\"key\": \"RES\""+ 
    " },"+ 
    "\"summary\": \"REST automated issue creation\","+ 
    "\"description\": \"Creating of an issue using automated script\","+ 
    "\"issuetype\": {"+ 
    "\"name\": \"Bug\""+ 
    "}"+  
    "}}";
        return postCreateIssue;
}

public static String postBodyAddcomment(){
    String postAddcomment="{\"body\": \"Add comment via Automation\","+
            "\"visibility\": {"+
            "\"type\": \"role\","+
            "\"value\": \"Administrators\"}"+
    "}";
    return postAddcomment;
}

public static String postBodyUpdateComment(){
    String postUpdateComment="{\"body\": \"NEW comment -Updated comment via Automation\","+
            "\"visibility\": {"+
            "\"type\": \"role\","+
            "\"value\": \"Administrators\"}"+
    "}";
    return postUpdateComment;    
}
}
