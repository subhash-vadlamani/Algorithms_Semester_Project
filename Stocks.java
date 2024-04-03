import java.util.Scanner;
import src.Alg1;
import src.Alg2;
import src.Alg3_1;
import src.Alg3_2;
import src.Alg4;
import src.Alg5;


class Stocks{
    public static void main(String[] args){
        String task = args[0];
//        Scanner in = new Scanner(System.in);
//        String task = in.next();
        System.out.println(task);
        String[] emptyArray = new String[0];

        if(task.equalsIgnoreCase("1")){
            System.out.println("Inside the if block");

            Alg1.main(emptyArray);
        }

        if(task.equalsIgnoreCase("2")){
            Alg2.main(emptyArray);

        }

        if(task.equalsIgnoreCase("3a")){
            Alg3_1.main(emptyArray);
        }

        if(task.equalsIgnoreCase("3b")){
            Alg3_2.main(emptyArray);
        }

        if(task.equalsIgnoreCase("4")){
            Alg4.main(emptyArray);
        }

        if(task.equalsIgnoreCase("5")){
            Alg5.main(emptyArray);
        }

    }

}