import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PolynomialModel
    extends AbstractTableModel {

    private ArrayList<Polynomial> polynomials;
    private ArrayList<RepaintListener> repaintListeners;

    public PolynomialModel(){
        polynomials = new ArrayList<>();
        repaintListeners = new ArrayList<>();
    }

    public ArrayList<Polynomial> getPolynomials() {
        return polynomials;
    }

    @Override
    public int getRowCount() {
        return polynomials.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Boolean.class;
            case 1:
                return String.class;
            case 2:
                return Color.class;
                default:
                    return super.getColumnClass(columnIndex);
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return true;
            case 2:
                return true;
                default:
                    return false;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Draw";
            case 1:
                return "Represantation";
            case 2:
                return "Color";
                default:
                    return super.getColumnName(column);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Polynomial polynomial = polynomials.get(rowIndex);
        switch (columnIndex){
            case 0:
                return polynomial.isChecked();
            case 1:
                return polynomial.getName();
            case 2:
                return polynomial.getColor();
                default:
                    return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Polynomial polynomial = polynomials.get(rowIndex);
        switch (columnIndex){
            case 0:
                polynomial.setIsChecked((Boolean) aValue);
                fireRepaintListener();
                break;
            case 1:
                polynomial.setName( (String) aValue);
                break;
            case 2:
                polynomial.setColor((Color) aValue);
                System.out.println("beeep");
                fireRepaintListener();
                break;
        }
    }

    public void addRepaintListener(RepaintListener repaintListener){
        repaintListeners.add(repaintListener);
    }

    public void fireRepaintListener(){
        for(RepaintListener repaintListener : repaintListeners)
            repaintListener.polynomialChange();
    }
}
