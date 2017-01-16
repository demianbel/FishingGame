package accounts;

import dataSets.UserDataSet;

/**
 * Created by Демьян on 09.01.2017.
 */
public interface AccountServiceI {
    void addUser(UserDataSet user);
    UserDataSet getUserByLogin(String login, String password);
}
