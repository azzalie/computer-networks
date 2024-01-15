import java.util.Scanner;

public class Solver {
    double a, b, c;

    Solver(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    Solver(Scanner scanner) {
        a = scanner.nextFloat();
        b = scanner.nextFloat();
        c = scanner.nextFloat();
    }

    public void solve() {
        System.out.printf("a = %f, b = %f, c = %f%s", a, b, c, "\n");

        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    System.out.println("Решений бесконечно\n");
                } else {
                    System.out.println("Решений нет\n");
                }
            } else {
                double x = -c / b;
                System.out.printf("x = %f%s", x, "\n");
            }
        } else {
            double D = Math.pow(b, 2) - 4 * a * c;
            double e = Double.MIN_VALUE;
            if ((D + e) < 0) {
                System.out.println("Нет рациональных решений\n");
                System.out.println();

            } else if (Math.abs(D) < e) {
                double x = -b / (2 * a);
                System.out.printf("x = %f%s", x, "\n");

            } else {
                double x_1 = (-b + Math.sqrt(D)) / (2 * a);
                double x_2 = (-b - Math.sqrt(D)) / (2 * a);
                System.out.printf("x1 = %f, x2 = %f%s", x_1, x_2, "\n");
            }
        }
    }
}
