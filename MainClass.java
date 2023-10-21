import javax.swing.*;
class MainClass{
    public static void main(String[] args) {
        JFrame f =new JFrame("Brick breaker BBB");
        f.setSize(710, 700);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setResizable(false);
        
        f.setLayout(null);
        f.setIconImage(new ImageIcon("Capture.png").getImage());

        GamePlay g = new GamePlay();
        f.add(g);
        f.setVisible(true);
    }
}