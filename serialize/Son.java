package serialize;

public class Son extends Father {
    private static final long serialVersionUID=1L;
    private String name;
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }

}
