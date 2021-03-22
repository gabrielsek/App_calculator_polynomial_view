
import javax.swing.*;
import java.awt.*;

public
    class MyDisplay
    extends JPanel
    implements CalcListener{

    JTextField memory;
    JTextField jtf;
    Double actualValue;
    Double displayValue;
    String latestOperation = "";
    int system;

    public MyDisplay(){
        this.setLayout(new BorderLayout());

        memory = new JTextField();
        memory.setForeground(Color.DARK_GRAY);
        memory.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        memory.setText("");
        memory.setEditable(false);
        memory.setSize(100, 200);

        jtf = new JTextField();
        jtf.setText("0.0");
        jtf.setSize(200, 100);
        jtf.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        jtf.setEditable(false);
        actualValue = 0.;
        displayValue = 0.;
        system = 10;

        this.add(memory, BorderLayout.PAGE_END);
        this.add(jtf, BorderLayout.PAGE_START);
    }

    @Override
    public void digitPressed(MyKeyPressed evt) {
        String input = "";
        switch (system){
            case 2:
                switch (evt.getValue()){
                    case "0":
                    case "1":
                        input = evt.getValue();
                        break;
                }
                break;
            case 8:
                switch (evt.getValue()){
                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                        input = evt.getValue();
                        break;
                }
                break;
            case 10:
                switch (evt.getValue()){
                    case ".":
                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":
                    case "10":
                        input = evt.getValue();
                        break;
                }
                break;
            case 16:
                switch (evt.getValue()){
                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":
                    case "10":
                    case "A":
                    case "B":
                    case "C":
                    case "D":
                    case "E":
                    case "F":
                        input = evt.getValue();
                        break;
                }
        }
        if(!input.equals("") && (jtf.getText().equals("0.0") || jtf.getText().equals("0")) )
            jtf.setText(input);
        else
            jtf.setText(jtf.getText()+input);
        memory.setText(memory.getText() + input);
    }

    @Override
    public void operationPressed(MyKeyPressed evt) {
        Double help = 0.;
        memory.setText(memory.getText() + evt.getValue());
        if(!jtf.getText().equals("-")) {
            switch (system) {
                case 10:
                    displayValue = Double.parseDouble(jtf.getText());
                    break;
                default:
                    if(!jtf.getText().equals("0.0")) {
                        String str = jtf.getText();
                        if(str.contains("."))
                            str = str.substring(0,str.length() - 2);
                        try {
                            displayValue = (double) Integer.parseInt(str, system);
                        }catch (NumberFormatException ex){
                            JOptionPane.showMessageDialog(this, "Zla operacja !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    else displayValue = 0.;
                    break;
            }
        }else
            displayValue = 0.0;

        switch (evt.getValue()){
            case "+":
                actualValue += displayValue;
                latestOperation = "+";
                jtf.setText("0.0");
                break;
            case "-":
                if(actualValue != 0)
                actualValue = actualValue - displayValue;
                else
                    actualValue = displayValue;
                latestOperation = "-";
                jtf.setText("0.0");
                break;
            case "*":
                if(actualValue != 0)
                    actualValue *= displayValue;
                else
                    actualValue = displayValue;
                latestOperation = "*";
                jtf.setText("0.0");
                break;
            case "/":
                if (actualValue != 0)
                    actualValue /= displayValue;
                else
                    actualValue = displayValue;
                latestOperation = "/";
                jtf.setText("0.0");
                break;
            case "=":
                switch (latestOperation){
                    case "+":
                        actualValue += displayValue;
                        jtf.setText("0.0");
                        break;
                    case "-":
                        actualValue = actualValue - displayValue;
                        jtf.setText("0.0");
                        break;
                    case "*":
                        actualValue *= displayValue;
                        jtf.setText("0.0");
                        break;
                    case "/":
                        if(displayValue !=0)
                        actualValue /= displayValue;
                        else
                            JOptionPane.showMessageDialog(this, "Nie dzielimy przez 0", "Warning", JOptionPane.WARNING_MESSAGE);
                        jtf.setText("0.0");
                        break;
                    case "x^y":
                        actualValue = Math.pow(actualValue, displayValue);
                        jtf.setText("0.0");
                        break;
                    case "%":
                        actualValue %= displayValue;
                        jtf.setText("0.0");
                        break;
                }
                switch (system) { /// do testow systemy
                    case 10:
                        jtf.setText(actualValue.toString());
                        memory.setText(memory.getText() + actualValue.toString());
                        break;
                    default:
                        String cut = actualValue.toString();
                        cut = cut.substring(0, cut.length() - 2);
                        Integer var = Integer.parseInt(cut);
                        //Integer fin = Integer.parseInt((Integer.toString(var ,system))); // ale dziala
                        jtf.setText(Integer.toString(var ,system));
                        memory.setText(memory.getText() + Integer.toString(var, system));
                        break;
                }
                actualValue = 0.;
                break;
            case "CE":
                jtf.setText("0.0");
                break;
            case "C":
                memory.setText("");
                jtf.setText("0.0");
                actualValue = 0.;
                break;
            case "<-":
                if(jtf.getText().length() > 1)
                    jtf.setText(jtf.getText().substring(0,jtf.getText().length() - 1));
                else
                    jtf.setText("0.0");
                break;
            case "-+":
                if(!jtf.equals("0.0")) {
                    if(jtf.getText().charAt(0) == '-')
                        jtf.setText(jtf.getText().substring(1,jtf.getText().length()));
                    else
                        jtf.setText("-" + jtf.getText());
                }
                break;
            case "sin":
                help = Math.sin(displayValue);
                jtf.setText(help.toString());
                break;
            case "cos":
                help = Math.cos(displayValue);
                jtf.setText(help.toString());
                break;
            case "tan":
                help = Math.tan(displayValue);
                jtf.setText(help.toString());
                break;
            case "log":
                if(displayValue > 0) {
                    help = Math.log10(displayValue);
                    jtf.setText(help.toString());
                }else {
                    JOptionPane.showMessageDialog(this, "Zle Dane !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                    jtf.setText("0.0");
                }
                break;
            case "ln":
                if(displayValue > 0) {
                    help = Math.log(displayValue);
                    jtf.setText(help.toString());
                }else {
                    JOptionPane.showMessageDialog(this, "Zle Dane !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                    jtf.setText("0.0");
                }
                break;
            case "x^2":
                help = Math.pow(displayValue,2);
                jtf.setText(help.toString());
                break;
            case "x^3":
                help = Math.pow(displayValue,3);
                jtf.setText(help.toString());
                break;
            case "x^y":
                if(actualValue == 0.0)
                actualValue = displayValue;
                else
                    actualValue = Math.pow(actualValue, displayValue);
                latestOperation = "x^y";
                jtf.setText("0.0");
                break;
            case "sqrt":
                if(displayValue > 0) {
                    help = Math.sqrt(displayValue);
                    jtf.setText(help.toString());
                }else {
                    JOptionPane.showMessageDialog(this, "Zle Dane !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                    jtf.setText("0.0");
                }
                break;
            case "%":
                if (actualValue != 0)
                    actualValue %= displayValue;
                else
                    actualValue = displayValue;
                latestOperation = "%";
                jtf.setText("0.0");
                break;
            case "HEX":
                system = 16;
                String cut = displayValue.toString();
                cut = cut.substring(0, cut.length() - 2);
                Integer var = Integer.parseInt(cut);
                jtf.setText(Integer.toString(var ,system));
                break;
            case "DEC":
                system = 10;
                cut = displayValue.toString();
                cut = cut.substring(0, cut.length() - 2);
                var = Integer.parseInt(cut);
                jtf.setText(Integer.toString(var ,system));
                break;
            case "OCT":
                system = 8;
                cut = displayValue.toString();
                cut = cut.substring(0, cut.length() - 2);
                var = Integer.parseInt(cut);
                jtf.setText(Integer.toString(var ,system));
                break;
            case "BIN":
                system = 2;
                cut = displayValue.toString();
                cut = cut.substring(0, cut.length() - 2);
                var = Integer.parseInt(cut);
                jtf.setText(Integer.toString(var ,system));
                break;
        }
    }
}
