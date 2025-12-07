import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Dual_ascent dual_ascent = new Dual_ascent("TestDualAscent", 3);
        dual_ascent.readData("data.txt");
        dual_ascent.solve_dual_ascent();
        dual_ascent.display_solution();

    }

}
