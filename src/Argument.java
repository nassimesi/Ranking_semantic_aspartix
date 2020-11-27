import java.util.ArrayList;

public class Argument {
    /**
     * Le nom de l'argument
     */
    private String name;
    /**
     * La liste des relations attasuant un argument
     */
    ArrayList<Relation> relations ;
    double cat;

    /**
     * Le constructeur de la classe Argument
     * @param name le nom de l'argument
     */
    public Argument(String name){
        this.name = name;
        relations = new ArrayList<Relation>();
    }
    /**
     * Add an attacker to argument
     * @param index la relation à ajouter
     */
    public void addRelation(Relation index){
        relations.add(index);
    }


    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Argument)obj).name);
    }

    @Override
    public String toString() {
        String s = "argument : "+this.name + " with cat : "+this.cat;
        return s;
    }
}
