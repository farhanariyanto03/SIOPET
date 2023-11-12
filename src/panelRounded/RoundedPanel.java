
package panelRounded;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class RoundedPanel extends JPanel{
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int arcSize = 50; // Ubah ukuran ini sesuai dengan keinginan Anda

        g.setColor(getBackground());
        g.setColor(Color.WHITE);
        g.fillRoundRect(0, 0, width, height, arcSize, arcSize);
    }
}
