package activity_1;

public class Successor {
    public static void main(String[] args) {
        if (args.length == 1)
        {
            int value = Integer.parseInt(args[0]);
            System.out.println("The sucessor is: " + (value+1));
        }
        else
            System.out.println("You either didn't pass a number or passed more then one!");
    }
}
