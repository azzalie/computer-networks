import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SimpleServlet", value = "/SimpleServlet")
public class SimpleServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public SimpleServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aStr = request.getParameter("a");
        String bStr = request.getParameter("b");

        int a = Integer.parseInt(aStr);
        int b = Integer.parseInt(bStr);

        List<Integer> result = findNumbers(a, b);

        request.setAttribute("result", result);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private List<Integer> findNumbers(int a, int b) {
        List<Integer> result = new ArrayList<>();
        for (int i = a; i <= b; i++) {
            if (i % 11 == 0 && i % 13 == 0 && i % 17 == 0) {
                result.add(i);
            }
        }
        return result;
    }
}