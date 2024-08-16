import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for the initial guess
        System.out.print("Enter the initial guess (p0): ");
        double p0 = scanner.nextDouble();

        // Get user input for the tolerance
        System.out.print("Enter the tolerance: ");
        double tolerance = scanner.nextDouble();

        // Get user input for the maximum iteration
        System.out.print("Enter the maximum iteration: ");
        int maxIter = scanner.nextInt();

        // Run the function
        double root = newtonsMethod(p0, tolerance, maxIter);
        System.out.println("The root of the equation is approximately " + root);

        scanner.close();
    }

    public static double newtonsMethod(double p0, double tolerance, int maxIter) {
        // Print the initial guess
        System.out.println("p0 value is " + p0);

        // Newton's method
        for (int i = 0; i < maxIter; i++) {
            double p = p0 - f(p0) / df(p0);

            // Check the tolerance
            if (Math.abs(p - p0) < tolerance) {
                break;
            }

            p0 = p;
            System.out.println("p" + (i + 1) + " value is " + p0);
        }

        return p0;
    }

    // Defining the function
    public static double f(double x) {
        return -Math.pow(x, 3) - Math.cos(x);
    }

    // Defining the derivative of the function
    public static double df(double x) {
        return -3 * Math.pow(x, 2) + Math.sin(x);
    }
}