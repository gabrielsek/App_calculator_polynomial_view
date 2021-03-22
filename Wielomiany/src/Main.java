import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> {
                    new Main();
                }
        );
    }

    public Main(){
        CoordinateSystem coordinateSystem = new CoordinateSystem();
        MyDisplay myDisplay = new MyDisplay();
        MyKeybord myKeybord = new MyKeybord();
        myKeybord.addCalcListener(myDisplay);

        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.add("Calculator", myDisplay);
        jTabbedPane.add("Polynomial", coordinateSystem);
        jTabbedPane.setPreferredSize(new Dimension(coordinateSystem.getSystemSize()*2 + 5,coordinateSystem.getSystemSize()*2));

        MemoryComponent memoryComponent = new MemoryComponent();
        memoryComponent.setRepaintListener(coordinateSystem);
        coordinateSystem.setPolynomials(memoryComponent.getPolynomialModel().getPolynomials());

        this.setLayout(new BorderLayout());
        this.add(memoryComponent, BorderLayout.LINE_END);
        this.add(jTabbedPane, BorderLayout.LINE_START);
        this.add(myKeybord, BorderLayout.PAGE_END);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setName("Calculator");
        this.setBackground(Color.DARK_GRAY);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

}
