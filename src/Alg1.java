package src;
import java.util.Scanner;

public class Alg1 {
    public static void main(String[] args){
        int m, n, i , j, k;

        Scanner in = null;
        try{
            in = new Scanner(System.in);
            System.out.println("Enter the number of stocks 'm' and the number of days 'n'.");

            m = in.nextInt();
            n = in.nextInt();

            System.out.println("The number of stocks entered are: "+m + " and the number of days entered are :" + n);
            System.out.println("Enter the values of the stock matrix: ");

            int stockPriceMatrix[][] = new int[m][n];
            for(i=0;i<m;i++){
                for(j=0;j<n;j++){
                    stockPriceMatrix[i][j] = in.nextInt();
                }
            }

            System.out.println("The elements of the stock price matrix are as follows: ");
            for(i=0;i<m;i++){
                for(j=0;j<n;j++){
                    System.out.print(stockPriceMatrix[i][j] + " ");
                }
                System.out.println();
            }

            int maxProfitSoFar = Integer.MIN_VALUE; // stores the maximum profit that we have encountered so far
            int stockBuyDay = 0; // this variable is used to store the final stock buy day
            int stockSellDay = 0; // this variable is used to store the final stock sell day
            int current_stock, buyDay, sellDay, mostProfitableStock=0;



            for(i=0;i<m;i++){
                current_stock = i + 1;
                for(j=0;j<n;j++){
                    buyDay = j + 1;
                    for(k=j+1;k<n;k++){
                        sellDay = k + 1;
                        // here, we compare the current profit that we can make with the
                        // maximum profit that we have achieved so far. If the current profit
                        // is greater than the maximum profit that has been achieved so far, we store
                        // this particular value as the max profit.
                        if (stockPriceMatrix[i][k] - stockPriceMatrix[i][j] > maxProfitSoFar){
                            maxProfitSoFar = stockPriceMatrix[i][k] - stockPriceMatrix[i][j];
                            stockBuyDay = buyDay;
                            stockSellDay = sellDay;
                            mostProfitableStock = current_stock;
                        }


                    }
                }
            }

            System.out.println("The most profitable stock, the max profit achieved so far, the stock buy day and the stock sell day are as follows:");
            System.out.println(mostProfitableStock + " "+maxProfitSoFar + " " + stockBuyDay + " " + stockSellDay);


        }
        catch (Exception e){


        }
        finally {
            in.close();
        }
    }
}