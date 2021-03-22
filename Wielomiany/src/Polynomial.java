import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial
    implements Comparable<Polynomial>{
    private Boolean isChecked;
    private String name;
    private Color color;
    private ArrayList<Integer> prefix = new ArrayList<>();
    private ArrayList<Integer> sufix = new ArrayList<>();

    public Polynomial (String path) throws WrongPolynomialException{
        Pattern check = Pattern.compile("(([+-]?\\d*)x(\\^(\\d)+)?)*([+-]?\\d+)?");
        Matcher matcher = check.matcher(path);
        Pattern pattern = Pattern.compile("([+-]?\\d*)x(\\^(\\d)+)?|([+-]?\\d+)");

        if(matcher.matches()) {
            matcher = pattern.matcher(path);

            color =  Color.RED;
            name = path;
            isChecked = false;

            while (matcher.find()) {
                if (matcher.group(1) != null && matcher.group(1).length() > 1) {
                    String str = matcher.group(1);
                    str = str.replace('+', '0');
                    prefix.add(Integer.parseInt(str));
                } else if (matcher.group(4) == null) {
                    if (matcher.group(1).contains("-"))
                        prefix.add(-1);
                    else
                        prefix.add(1);
                }

                if (matcher.group(3) != null)
                    sufix.add(Integer.parseInt(matcher.group(3)));
                else if (matcher.group(3) == null && matcher.group(4) == null)
                    sufix.add(1);

                if (matcher.group(4) != null) {
                    //System.out.println(Integer.parseInt(matcher.group(4)));
                    prefix.add(Integer.parseInt(matcher.group(4)));
                    sufix.add(0);
                }
            }
        }else throw new WrongPolynomialException();
    }

    public double valueAtX(double x){
        Double value = 0.0;
        for(int i = 0; i < prefix.size(); i++){
            if(sufix.get(i) != 0){
                value += prefix.get(i) * Math.pow(x, sufix.get(i));
            }else
                value += prefix.get(i);
        }
        return value;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setIsChecked(boolean var){
        isChecked = var;
    }

    public boolean isChecked(){
        return isChecked;
    }

    public void dodraw(Graphics g, double scale, int size){
        g.setColor(color);
        for(int i = size*(-1); i < size; i++){
            g.drawLine((size + i),size - (int)(valueAtX(i*scale)), (size + (i+1)), size - (int)(valueAtX((i+1)*scale)));
        }
        System.out.println("SCALE " + scale);
        System.out.println("COLOR" + color);
    }

    @Override
    public int compareTo(Polynomial o) {
        return name.compareTo(o.name);
    }
}
