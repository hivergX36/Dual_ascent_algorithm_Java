
public class Main {
    public static void main(String[] args) {
        Dual_ascent da = new Dual_ascent("TestDualAscent", 3);
        da.readData("data.txt");
        da.display_equations();
    }

}
