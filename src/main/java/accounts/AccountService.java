package accounts;

import dataSets.UserDataSet;
import database.DBException;
import database.DataBaseService;

/**
 * Created by Демьян on 09.01.2017.
 */
public class AccountService implements AccountServiceI{
    private DataBaseService dataBaseService;

    public AccountService(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void addUser(UserDataSet user){
        try {
          if (dataBaseService.getUser(user.getName()) == null) {
             dataBaseService.addUser(user);
          }
        } catch (DBException e){
            System.out.println("can't connect to DB (addUser)");
        }

    }
    public UserDataSet getUserByLogin(String login, String password){
        try {
            UserDataSet user = dataBaseService.getUser(login);
            if(user != null && user.getPassword().equals(password)){
                return user;
            } return null;
        } catch (DBException e){
            System.out.println("can't connect to DB (getUserByName)");
        }
        return null;
    }
}
