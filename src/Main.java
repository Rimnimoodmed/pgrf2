
import controller.Controller3D;
import view.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window(300, 300);
        new Controller3D(window.getPanel());
    }
}