import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemoryComponent
    extends JPanel {

    private JButton minusPolynomial;
    private JButton plusPolynomial;
    private PolynomialModel polynomialModel;

    public MemoryComponent() {
        polynomialModel = new PolynomialModel();
        TableModel tableModel = polynomialModel;
        JTable table = new JTable(tableModel);
        TableColumn tableColumn = table.getColumnModel().getColumn(2);
        ColorEditor colorEditor = new ColorEditor();
        colorEditor.addPolynomialModel(polynomialModel);
        tableColumn.setCellEditor(colorEditor);
        tableColumn.setCellRenderer(new MyRenderer());
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(table);

        plusPolynomial = new JButton("+");
        plusPolynomial.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String str = "";
                        str = JOptionPane.showInputDialog("Enter the polynomial");
                        if(str != null) {
                            try {
                                boolean isAlready = true;
                                Polynomial polynomial = new Polynomial(str);
                                for (Polynomial iterator : polynomialModel.getPolynomials()) {
                                    if (polynomial.compareTo(iterator) == 0)
                                        isAlready = false;
                                }
                                if (isAlready)
                                    polynomialModel.getPolynomials().add(polynomial);
                                table.updateUI();
                                polynomialModel.fireRepaintListener();
                            } catch (WrongPolynomialException ex) {
                                JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(plusPolynomial), "Try again", "WRONG POLYNOMIAL", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                }
        );

        minusPolynomial = new JButton("-");
        minusPolynomial.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String str = "";
                        str = JOptionPane.showInputDialog("Enter the polynomial");
                        if(str != null) {
                            try {
                                Polynomial polynomial = new Polynomial(str);
                                for (Polynomial iterator : polynomialModel.getPolynomials()) {
                                    if (polynomial.compareTo(iterator) == 0)
                                        polynomial = iterator;
                                }
                                polynomialModel.getPolynomials().remove(polynomial);
                                table.updateUI();
                                polynomialModel.fireRepaintListener();
                            } catch (WrongPolynomialException ex) {
                                JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(plusPolynomial), "Try again", "WRONG POLYNOMIAL", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                }
        );
        Box box = new Box(2);
        plusPolynomial.setBackground(Color.PINK);
        minusPolynomial.setBackground(Color.PINK);
        box.add(plusPolynomial);
        box.add(minusPolynomial);
        this.setLayout(new BorderLayout());
        this.add(jScrollPane, BorderLayout.PAGE_START);
        this.add(box, BorderLayout.PAGE_END);
        //this.add(plusPolynomial, BorderLayout.LINE_START);
        //this.add(minusPolynomial, BorderLayout.LINE_END);
    }

    public void setRepaintListener(CoordinateSystem coordinateSystem){
        polynomialModel.addRepaintListener(coordinateSystem);
    }

    public PolynomialModel getPolynomialModel() {
        return polynomialModel;
    }
}
