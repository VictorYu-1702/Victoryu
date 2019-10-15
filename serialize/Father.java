package serialize;

import java.io.Serializable;

public class Father implements Serializable {
    private static final long serialVersionUID=1L;
    private int age;
    public void setAge(int age){
        this.age=age;
    }

    public int getAge() {
        return age;
    }
}
