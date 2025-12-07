import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dual_ascent {
    String name;
    Dictionnary dict;
    float x_opt;
    float lambda_opt;
    boolean test_quadratic_program = true;
    boolean test_linear_program = true;

    Dual_ascent(String name, int dictSize) {
        this.name = name;
        this.dict = new Dictionnary(dictSize);
        this.x_opt = 0;
        this.lambda_opt = 0;
    }

    void readData(String namefile) {
        // function for parsing the file and filling dictionnary

        File file = new File(namefile);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break; // Stop on blank line
                }
                System.out.println("line: " + line);
                String[] parts = line.split(":");
                String key = parts[0];
                System.out.println("key: " + key);
                float value = Float.parseFloat(parts[1]);
                System.out.println("value: " + value);
                dict.insert(key, value);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

    }

    void test_quadratic_program() {
        float a1 = dict.get("a1x2");
        float a2 = dict.get("a2x2");
        // One of the quadratic coefficients must be difference of zero
        if (a1 != 0 || a2 != 0) {
            this.test_quadratic_program = true;
        } else {
            this.test_quadratic_program = false;
        }
    }

    void test_linear_program() {
        float b2 = dict.get("b2x");
        if (b2 != 0) {
            this.test_linear_program = true;
        } else {
            this.test_linear_program = false;
        }

    }

    float calulate_solution_linear_programming() {
        float c2 = dict.get("c2");
        float b2 = dict.get("b2");
        float x = c2 / b2;
        return x;

    }

    float Min_Lagrangien_function(float lambda) {
        // L(x,lambda) = a1*x^2 + b1*x + c1 + lambda*(c2 - a2x^2 - b2x)
        // Derivative: L'(x,lambda) = 2*a1*x + b1 - 2*a2*lambda*x - b2*lambda
        // Solving for x: x = -(b1 - b2*lambda) / (2*(a1 - a2*lambda))
        float x = 0;
        float a1 = dict.get("a1x2");
        float b1 = dict.get("b1x");
        float a2 = dict.get("a2x2");
        float b2 = dict.get("b2x");

        float numerator = b2 * lambda - b1;
        ;
        float denominator = 2 * (a1 - lambda * a2);
        if (denominator != 0) {
            x = numerator / denominator;
        } else {
            System.out.println("Denominator is Null, we have a linear program with one constraint.");
            x = 0; // or some default value or error handling
        }

        return x;

    }

    float update_lambda(float x, float lambda) {
        float c2 = dict.get("c2");
        float a2 = dict.get("a2x2");
        float b2 = dict.get("b2x");
        float constraint_value = c2 - a2 * x * x - b2 * x;
        float lambda_new = Math.max(0, lambda + 0.1f * constraint_value); // 0.1 is the step size
        return lambda_new;

    }

    void display_equations() {
        // display dictionnary content
        System.out.println("equations content:");
        dict.display_dictionary();

    }

    void display_the_program() {
        System.out.println("Program for " + this.name + ":");
        System.out.println("Objective function: ");
        System.out.print("Minimize L(x, lambda) = ");
        System.out.print(dict.get("a1x2") + "x^2 + ");
        System.out.print(dict.get("b1x") + "x + ");
        System.out.print(dict.get("c1") + " + ");
        System.out.print("lambda * (");
        System.out.print(dict.get("c2") + " - ");
        System.out.print(dict.get("a2x2") + "x^2 - ");
        System.out.print(dict.get("b2x") + "x)");
        System.out.println();
        System.out.println("Subject to: ");
        System.out.print(dict.get("a2x2") + "x^2 + ");
        System.out.print(dict.get("b2x") + "x <= ");
        System.out.print(dict.get("c2"));
        System.out.println();

    }

    void solve_dual_ascent() {
        float epsilon = 0.00001f;
        float previous_lambda = 0;
        int max_iterations = 1000000;
        int iteration = 0;
        float x_t = 0;
        float lambda_t = 0;
        this.test_quadratic_program();
        test_linear_program();
        if (!this.test_quadratic_program) {
            if (!this.test_linear_program) {
                System.out.println("This is not a quadratic program nor a linear program.");
                return;
            } else {
                System.out.println("This is a linear program with one constraint.");
                float x_opt = calulate_solution_linear_programming();
                System.out.println("Optimal solution x*: " + x_opt);
                return;
            }

        }
        while (iteration < max_iterations) {
            // Step 1: Minimize L(x, lambda) with respect to x
            x_t = this.Min_Lagrangien_function(previous_lambda);

            // Step 2: Update lambda
            lambda_t = this.update_lambda(x_t, previous_lambda);
            System.out.println("Iteration " + iteration + ": x = " + x_t + ", lambda = " + lambda_t);

            // Check for convergence
            if (Math.abs(lambda_t - previous_lambda) < epsilon) {
                break;
            }
            previous_lambda = lambda_t;
            iteration++;
        }

        this.x_opt = x_t;
        this.lambda_opt = lambda_t;

    }

    void display_solution() {
        System.out.println("Dual Ascent Solution for " + this.name + ":");
        System.out.println("Optimal solution x*: " + this.x_opt);
        System.out.println("Optimal Lagrange multiplier lambda*: " + this.lambda_opt);

    }

}
