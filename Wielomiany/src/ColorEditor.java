import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ColorEditor
        extends AbstractCellEditor
        implements TableCellEditor {

    private JButton button;
    private ArrayList<PolynomialModel> polynomialModels;
    private Color savedColor;

    public ColorEditor() {
        polynomialModels = new ArrayList<>();
        button = new JButton();
        button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        Color color = JColorChooser.showDialog(button, "Choose color", savedColor);
                        changeColor(color);
                        fire();
                    }
                }
        );
    }

    @Override
    public Object getCellEditorValue() {
        return savedColor;
    }

    private void changeColor(Color color) {
        if (color != null) {
            savedColor = color;
            button.setBackground(color);
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        changeColor((Color) value);
        return button;
    }

    public void addPolynomialModel(PolynomialModel polynomialModel) {
        polynomialModels.add(polynomialModel);
    }

    public void fire(){
        for(PolynomialModel polynomialModel : polynomialModels)
            polynomialModel.fireTableDataChanged();
    }
}