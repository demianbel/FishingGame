package servlets;

import accounts.AccountService;
import accounts.AccountServiceI;
import dataSets.UserDataSet;
import database.DataBaseService;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Демьян on 11.01.2017.
 */
public class SignUpServlet extends HttpServlet {
    private Map<String, UserDataSet> usersMap;
    private DataBaseService dataBaseService;

    public SignUpServlet(Map<String, UserDataSet> usersMap, DataBaseService dataBaseService) {
        this.usersMap = usersMap;
        this.dataBaseService = dataBaseService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AccountServiceI accountService = new AccountService(dataBaseService);
        UserDataSet user = new UserDataSet(login, password);
        accountService.addUser(user);
        user = accountService.getUserByLogin(login, password);

        Map<String, String> pageVariables;
        pageVariables = new HashMap<>();
        pageVariables.put("username", login);

        resp.setContentType("text/html;charset=utf-8");
        if (user == null) {
            resp.getWriter().println(PageGenerator.instance().getPage("nouser.html", pageVariables));
        } else {
            usersMap.put(req.getSession().getId(), user);
            resp.getWriter().println(PageGenerator.instance().getPage("fishing.html", pageVariables));
        }
    }
}
