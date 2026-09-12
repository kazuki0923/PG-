package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CustomerSearchAction;

/**
 * KIDDA-LA業務システムのフロントコントローラ。
 */
@WebServlet("/KiddaLaController")
public class KiddaLaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String command = request.getParameter("command");
        String nextPage = "/MainMenu.jsp";

        if (command == null || command.equals("")) {
            nextPage = "/MainMenu.jsp";

        } else if (command.equals("CustomerSearchDisplay")) {
            session.removeAttribute("customerData");
            request.setAttribute("telNo", "");
            request.setAttribute("customerName", "");
            nextPage = "/CustomerSearch.jsp";

        } else if (command.equals("CustomerSearch")) {

            String telNo = safe(request.getParameter("telNo"));
            String customerName = safe(request.getParameter("customerName"));

            request.setAttribute("telNo", telNo);
            request.setAttribute("customerName", customerName);
            session.removeAttribute("customerData");

            String normalizedTel = removeSpaces(telNo);
            String normalizedKana = removeSpaces(customerName);

            if (normalizedTel.equals("") && normalizedKana.equals("")) {
                request.setAttribute("errorCode", "011");

            } else if (!isValidTel(normalizedTel)
                    || !isValidKana(normalizedKana)) {
                request.setAttribute("errorCode", "012");

            } else {
                try {
                    CustomerSearchAction action = new CustomerSearchAction();
                    String[][] customerData = action.execute(
                            new String[] { telNo, customerName });

                    if (customerData == null || customerData.length == 0) {
                        request.setAttribute("errorCode", "012");
                    } else {
                        session.setAttribute("customerData", customerData);
                    }

                } catch (Exception e) {
                    request.setAttribute("errorCode", "010");
                }
            }

            nextPage = "/CustomerSearch.jsp";

        } else if (command.equals("CustomerSelect")) {
            request.setAttribute("custId",
                    safe(request.getParameter("custId")));
            nextPage = "/OrderDeliveryCustomerChange.jsp";
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }

    private String removeSpaces(String value) {
        return value.replace(" ", "").replace("　", "");
    }

    private boolean isValidTel(String tel) {
        return tel.equals("") || tel.matches("[0-9]+");
    }

    private boolean isValidKana(String kana) {
        return kana.equals("") || kana.matches("[ァ-ヶー]+");
    }
}
