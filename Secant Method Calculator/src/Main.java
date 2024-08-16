import java.util.ArrayList;
import java.util.List;

public class Main {
    public static double f(double x) {
        return x - 0.8 - 0.2 * Math.sin(x); // equation here
    }

    public static void main(String[] args) {
        List<Double> p = new ArrayList<>();
        p.add(0.0);
        p.add(Math.PI / 2); // initial guesses

        int n = 11; // number of iterations

        // Print the initial values
        System.out.println("p0 = " + p.get(0) + ", f(p0) = " + f(p.get(0)));
        System.out.println("p1 = " + p.get(1) + ", f(p1) = " + f(p.get(1)));

        for (int i = 2; i <= n; i++) {
            double diff = f(p.get(i - 1)) - f(p.get(i - 2));
            if (diff == 0) {
                System.out.println("Division by zero detected, stopping iteration...");
                break;
            } else {
                double pn = p.get(i - 1) - ((f(p.get(i - 1)) * (p.get(i - 1) - p.get(i - 2))) / diff);
                p.add(pn);
                System.out.println("p" + i + " = " + pn + ", f(p" + i + ") = " + f(pn));
            }
        }

        System.out.println("p11 = " + p.get(11) + ", f(p11) = " + f(p.get(11)));
    }
}