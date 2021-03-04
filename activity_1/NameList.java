package activity_1;

import java.util.LinkedList;
import java.util.List;

public class NameList {

    public static void orderedInsert(String n, List<String> ordered){
        int insertPost=0;

        while(insertPost < ordered.size() && ordered.get(insertPost).compareTo(n)<0){
            insertPost++;
        }
        ordered.add(insertPost, n);
    }
    public static void main(String[] args) {
        List<String> names = new LinkedList<>();

        for(String n: args){
            names.add(n);
        }
        
        List<String> ordered = new LinkedList<>();
        for(String n: names){
            System.out.println("Going to insert: " + n);
            orderedInsert(n, ordered);
        }

        System.out.println("Before: " + names.toString() + "\nAfter: " + ordered.toString());
    }
}
