import java.util.ArrayList;

public class Relation {
    public String name;
    ArrayList<Integer> indices;
    public Relation(String name){
        this.name = name;
        indices = new ArrayList<>();
    }
    public void addMembers(int i){
        indices.add(i);
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Relation)obj).name);
    }
}
