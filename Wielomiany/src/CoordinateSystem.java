import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CoordinateSystem
    extends JPanel
    implements RepaintListener{

    private ArrayList<Polynomial> polynomials;
    private double scale;
    private int systemSize;

    public CoordinateSystem(){
        scale = 0.5;
        systemSize = 250;
        JButton zoomin = new JButton("+");
        zoomin.addActionListener(
                (e) ->{
                    if(scale > 0.2) {
                        scale -= 0.1;
                        repaint();
                    }
                }
        );
        JButton zoomout = new JButton("-");
        zoomout.addActionListener(
                (e) ->{
                    if(scale < 5.0) {
                        scale += 0.1;
                        repaint();
                    }
                }
        );
        this.setLayout(null);
        zoomin.setBounds(0,0, 50, 20);
        zoomin.setBackground(Color.WHITE);
        zoomout.setBounds(50,0, 50, 20);
        zoomout.setBackground(Color.WHITE);
        this.add(zoomin);
        this.add(zoomout);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(systemSize*2, systemSize*2));
    }

    public void setPolynomials(ArrayList<Polynomial> polynomials) {
        this.polynomials = polynomials;
    }

    public int getSystemSize() {
        return systemSize;
    }

    public void setSystemSize(int systemSize) {
        this.systemSize = systemSize;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawLine(0, systemSize, systemSize*2, systemSize); // os X
        g.drawLine(systemSize*2, systemSize, systemSize*2 - 10, systemSize - 10);// strzalka przy osi X w gore
        g.drawLine(systemSize*2, systemSize, systemSize*2 - 10, systemSize + 10); // strzalka przy osi X w dol
        g.drawLine(systemSize, 0, systemSize, systemSize*2 + 20); // os Y
        g.drawLine(systemSize, 0, systemSize - 10, 10); // strzalka przy osi Y w lewo
        g.drawLine(systemSize, 0, systemSize + 10, 10); // strzalka przy osi Y w prawo
        g.drawString("Y", systemSize + 5, 20);
        g.drawString("X", systemSize*2 - 10, systemSize + 20);
        for(int i = systemSize/5; i < systemSize*2; i+=(systemSize/5)) {
            Integer var = (int) ( (systemSize - i) * scale) * (-1);
            if(var != 0) {
                g.drawString(var.toString(), i - 10, systemSize + 15);
                g.drawLine(i, systemSize - 5, i, systemSize + 5);
                g.drawLine(systemSize - 5, i, systemSize + 5, i);
            }
            var = (int)systemSize - i;
            g.drawString(var.toString(), systemSize + 5, i + 5);
        }
        for(Polynomial polynomial : polynomials){
            if(polynomial.isChecked())
            polynomial.dodraw(g, scale, systemSize);
        }
    }

    @Override
    public void polynomialChange() {
        repaint();
    }
}
