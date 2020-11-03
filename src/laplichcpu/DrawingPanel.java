package laplichcpu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    protected void doDrawing(Graphics g) {
        Graphics2D g2d1 = (Graphics2D) g;
        g2d1.setColor(Color.BLUE);
        g2d1.fillRect(10 ,10 ,100, 100);
    }
}
