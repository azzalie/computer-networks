import java.util.Scanner;

public class QuadraticEquation {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

//        test_input(0, 0, 0); // Решений бесконечно
//        test_input(0, 0, 1); // Решений нет
//        test_input(0, 1, 2); // x = -2
//        test_input(1, 0, 4); // Нет рациональных решений
//        test_input(1, -4, 4); // x = 2
//        test_input(1, 3, 2); // x1 = -1, x2 = -2

//        test_scan(scan);

        Solver t1 = new Solver(0, 0, 0);
        t1.solve();
        Solver t2 = new Solver(0, 0, 1);
        t2.solve();
        Solver t3 = new Solver(0, 1, 2);
        t3.solve();
        Solver t4 = new Solver(1, 0, 4);
        t4.solve();
        Solver t5 = new Solver(1, -4, 4);
        t5.solve();
        Solver t6 = new Solver(1, 3, 2);
        t6.solve();

        Solver s = new Solver(scan);
        s.solve();
    }

//    static void test_input(double a, double b, double c) {
//        Quadratic_equation value = new Quadratic_equation(a, b, c);
//        value.solve();
//    }

//    static void test_scan(Scanner scan) {
//        Quadratic_equation value = new Quadratic_equation(scan);
//        value.solve();
//    }
}
