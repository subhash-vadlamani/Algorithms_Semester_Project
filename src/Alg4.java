package src;
import java.util.*;
import java.lang.*;

public class Alg4{

    public static void main(String[] args)
    {
        System.out.println("enter the number of transactions");
        Scanner sc=new Scanner(System.in);
        int numtrans=sc.nextInt();
        System.out.println("enter the number of stocks");
        int numofstocks=sc.nextInt();
        System.out.println("enter the number of days");
        int numofdays=sc.nextInt();
        ArrayList<ArrayList<Integer> > stockPriceMatrix
                = new ArrayList<ArrayList<Integer> >();
        for(int i=0;i<numofstocks;i++){
            System.out.println("enter the number ");
            for(int j=0;j<numofdays;j++){
                stockPriceMatrix.add(new ArrayList<Integer>());
                int p=sc.nextInt();
                stockPriceMatrix.get(i).add(j, p);
            }
        }

        System.out.println("the input is");
        for(int i=0;i<numofstocks;i++){
            System.out.println();
            for(int j=0;j<numofdays;j++){
                System.out.print(stockPriceMatrix.get(i).get(j) + " ");
            }
        }
        int rest=profitMaximum(stockPriceMatrix,numtrans,numofdays,numofstocks);
        System.out.println("the max profit is: "+rest);
    }
    public static int profitMaximum(ArrayList<ArrayList<Integer>> arrlist,int numtrans,int numofdays,int numofstocks){
        return transactionCalculator(arrlist, 0, -1, numtrans,numofdays,numofstocks);
    }
    public static int transactionCalculator(ArrayList<ArrayList<Integer>> arrlist,int i,int collected,int rem,int numofdays,int numofstocks){
        if(i==numofdays || rem==0){
            if(collected==-1 && rem==0)
                return 0;
            else{
                return -1000000000;
            }

        }
        int result=-1000000000;
        if(collected==-1){
            for(int s=0;s<numofstocks;s++){
                result=Math.max(result,transactionCalculator(arrlist,i+1,s,rem,numofdays,numofstocks)-arrlist.get(s).get(i));

            }

        }
        else{
            for(int j=0;j<numofstocks;j++){
                if(j!=collected){
                    result=Math.max(result,transactionCalculator(arrlist,i+1,j,rem-1,numofdays,numofstocks)-arrlist.get(j).get(i)+arrlist.get(collected).get(i));

                }

            }
            result=Math.max(result,transactionCalculator(arrlist,i+1,-1,rem-1,numofdays,numofstocks)+arrlist.get(collected).get(i));
        }
        result=Math.max(result,transactionCalculator(arrlist,i+1,collected,rem,numofdays,numofstocks));
        return result;
    }
}