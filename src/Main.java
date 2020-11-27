import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static double minCategoriser(ArrayList<Argument> a, ArrayList<Integer> c){
        ArrayList<Double> b = new ArrayList<>();
        for (int e:c
             ) {
            b.add(a.get(e).cat);
        }
        return Collections.min(b);
    }
    public static void categorizer(ArrayList<Argument> a){
        for (Argument e:a
             ) {
            e.cat = 1.0;
        }
        int maxSteps = 50;
        double precisionVal = 0.01;
        int i = 0;
        boolean isPrecisionAchieved = false;
        while (i < maxSteps && !isPrecisionAchieved){
            for (Argument e:a
                 ) {
                double sum = 1.0;
                for (Relation ee:e.relations
                     ) {
                    sum += minCategoriser(a,ee.indices);
                }
                isPrecisionAchieved &= (e.cat - (1.0/sum)) <= precisionVal;
                e.cat = 1 / sum;
            }
            i++;
        }
        for (Argument e:a
             ) {
            e.cat = (Math.round(e.cat*100)+0.0)/100;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.af"));
        ArrayList<Argument> arguments= new ArrayList<>();
        String line=in.readLine();;
        while (line!=null)
        {
            if (line.length()!=0) {
                String values[] = line.split("\\.");
                //System.out.println("size is : "+values.length);
                int partialIndex = -1;
                for (int i=0; i<values.length; i++){
                    if (values[i].contains("arg")) arguments.add(new Argument(values[i].substring(4,values[i].length()-1)));
                    else if (values[i].contains("att")) {
                        partialIndex = arguments.indexOf(new Argument(values[i].substring(4,values[i].length()-1).split(",")[1]));
                        arguments.get(partialIndex).addRelation(new Relation(values[i].substring(4,values[i].length()-1).split(",")[0]));
                    }
                    else {
                        arguments.get(partialIndex).relations.get(arguments.get(partialIndex).relations.indexOf(new Relation(values[i].substring(4,values[i].length()-1).split(",")[0]))).addMembers(arguments.indexOf(new Argument(values[i].substring(4,values[i].length()-1).split(",")[1])));
                    }
                }
            }
            line = in.readLine();
        }
        categorizer(arguments);
        for (Argument e:arguments
        ) {
            System.out.println(e.toString());
        }
    }
}
