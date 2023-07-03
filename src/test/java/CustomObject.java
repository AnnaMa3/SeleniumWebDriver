public class CustomObject {

    private final String name;
    private final String position;
    private final String office;

    public CustomObject(String name, String position, String office){
        this.name = name;
        this.position = position;
        this.office = office;
    }

    @Override
    public String toString(){
        return "User - " + "name: " + name + ", position: " + position + ", office: " + office;
    }
}
