import javax.swing.*;

public class GameFrame extends JFrame {
    private final GamePanel gamePanel;

    public void start() {
        gamePanel.startGame();
    }
    public GameFrame() {
        this.gamePanel = new GamePanel();
        this.add(gamePanel);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
