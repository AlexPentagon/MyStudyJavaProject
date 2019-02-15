import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        JFrame window = new JFrame("Xonix");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setResizable(false);
        window.setContentPane(new GamePanel());
        window.pack();
        window.setVisible(true);
    }
}