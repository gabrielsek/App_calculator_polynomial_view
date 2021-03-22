import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public
    class MyKeybord
    extends JPanel {

    JButton buttons[];
    String[] labels = {
            "HEX","DEC","CE","C","<-","/","sin","x^2",
      "OCT","BIN","7","8","9","+","cos","x^3",
      "A","B","4","5","6","-","tan","x^y",
      "C","D","1","2","3","*","log","sqrt",
      "E","F","-+","0",".","=","ln","%"
    };

    ArrayList<CalcListener> calcListeners = new ArrayList<>();

    public MyKeybord(){
        buttons = new JButton[labels.length];

        this.setLayout(new GridLayout(5, 6, 2, 2));

        for(int i=0; i<buttons.length; i++){
            buttons[i] = new JButton(labels[i]);
            buttons[i].setBackground(new Color(255, 253, 208));
            buttons[i].setForeground(Color.DARK_GRAY);
            buttons[i].setPreferredSize(new Dimension(15,50));
            this.add(buttons[i]);

            switch(labels[i]){
                case "+":
                case "-":
                case "*":
                case "/":
                case "=":
                case "CE":
                case "C":
                case "<-":
                case "-+":
                case "sin":
                case "cos":
                case "tan":
                case "log":
                case "ln":
                case "x^2":
                case "x^3":
                case "x^y":
                case "sqrt":
                case "%":
                case "HEX":
                case "DEC":
                case "OCT":
                case "BIN":
                    buttons[i].addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for( CalcListener calcListener : calcListeners){
                                    calcListener.operationPressed(
                                            new MyKeyPressed(
                                                    e.getSource(),
                                                    ((JButton)e.getSource()).getText()
                                            )
                                    );
                                }
                            }
                        }
                    );
                    break;

                    default:
                        buttons[i].addActionListener(
                            new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    for( CalcListener calcListener : calcListeners){
                                        calcListener.digitPressed(
                                                new MyKeyPressed(
                                                        e.getSource(),
                                                        ((JButton)e.getSource()).getText()
                                                )
                                        );
                                    }
                                }
                            }
                        );
            }


        }

    }

    public void addCalcListener(CalcListener calcListener){
        calcListeners.add(calcListener);
    }
}
