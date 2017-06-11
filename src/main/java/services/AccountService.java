package services;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lidiakulikova on 29/05/2017.
 */
public class AccountService {

    private static AccountService accountService;
    private static Map<String, String> credentialsMap;

    private AccountService(){
        credentialsMap = new HashMap<>();
    }

    public static AccountService instance() {
        if (accountService == null)
            accountService = new AccountService();
        return accountService;
    }

    public static void createUser(String login, String password){
        if (checkCredentials(login, password)){
            credentialsMap.put(login, password);
        }
    }

    public static boolean isUserExisted(String login, String password){
        if (credentialsMap.isEmpty()){
            return false;
        } else return credentialsMap.get(login).equals(password);

    }

    public static boolean checkCredentials(String login, String password){
        if ((login == null || login.isEmpty() ) && (password == null || password.isEmpty())){
            return false;
        }
        else{
            return true;
        }
    }
}
