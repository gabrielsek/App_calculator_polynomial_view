
import java.util.EventObject;

public
    class MyKeyPressed
    extends EventObject {

    String value;

    public MyKeyPressed(Object source, String value) {
        super(source);
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
