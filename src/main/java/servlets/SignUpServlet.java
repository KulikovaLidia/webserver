package servlets;

import services.AccountService;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static jdk.nashorn.internal.objects.Global.instance;
import static services.AccountService.createUser;

/**
 * Created by lidiakulikova on 28/05/2017.
 */
public class SignUpServlet extends HttpServlet {


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        String login = request.getParameter("login");
        String password =request.getParameter("password");

        response.setContentType("text/html;charset=utf-8");

        if ((login == null || login.isEmpty()) && password == null || password.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            pageVariables.put("message", "Введите данные для регистрации");
        } else {
            AccountService.instance().createUser(login, password);
            response.setStatus(HttpServletResponse.SC_OK);
            pageVariables.put("message", "Вы успешно зарегистрированы");
        }


        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("login", request.getParameter("login").toString());
        pageVariables.put("password", request.getParameter("password").toString());
        return pageVariables;
    }

}
