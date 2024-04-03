package src;
import java.util.Scanner;
import java.lang.*;
public class Alg3_1{
    public static int sellIndex;
    public static void main(String[] args){
        int m, n, i, j;

        Scanner in = null;

        try{
            System.out.println("Enter the number of stocks 'm' and the number of days 'n'.");

            in = new Scanner(System.in);

            m = in.nextInt();
            n = in.nextInt();

            System.out.println("The number of stocks entered are : "+ m +" and the number of days entered are: "+ n);

            int[][] stockPriceMatrix = new int[m][n];

            System.out.println("Enter the stock prices of "+ m + " stocks for "+ n + " days respectively.");

            for(i=0;i<m;i++){
                for(j=0;j<n;j++){
                    stockPriceMatrix[i][j] = in.nextInt();
                }
            }

            System.out.println("The stock prices that you have entered are : ");
            for(i=0;i<m;i++){
                for(j=0;j<n;j++){
                    System.out.print(stockPriceMatrix[i][j] + " ");
                }
                System.out.println();
            }

            int actualBuyDay=0, actualSellDay=0, s=0;
            int buyDay, sellDay,stock=0, maxProfit = Integer.MIN_VALUE;
            i=0;
            j=0;

            for(i=0;i<m;i++){
                int[] lmin = new int[n]; // Returns the index of the leftmost minimum stock
                int[] dp = new int[n]; // The dp array which stores the profit that can be achieved till a particular day.
                for(int k=0;k<n;k++){
                    lmin[k] = 0;
                    dp[k] = -1;
                }
                lmin[0] = 0;

                for(j =1;j<n;j++){
                    if(stockPriceMatrix[i][lmin[j-1]] < stockPriceMatrix[i][j]){
                        //Since the current matrix element that we are checking is greater than the previous minimum value,
                        // the minimum value of the stock that we encountered till now till index 'j' is the same as the
                        // minimum value that we have encountered till the index 'j-1'.
                        lmin[j] = lmin[j-1];
                    }
                    else{
                        //Since the matrix element that we are checking is less than the previous minimum value,
                        // the minimum value of the stock that we encountered till now till index 'j' is the new minimum
                        // value that we have encountered till now.
                        lmin[j] = j;
                    }

                }

                int sellIndex = 0;
                int profit = maxProf(stockPriceMatrix[i], lmin, n-1, sellIndex, dp);
                // The maximum profit that we can achieve with the stock i.
//                System.out.println("The profit is as follows : "+profit);

                if(profit > maxProfit) {
                    maxProfit = profit;

                    stock = i;
                    sellIndex = n - 1;
                    while (dp[sellIndex] == dp[sellIndex - 1] && sellIndex > 0) {
                        sellIndex-= 1;
                        //we are looking for the sellIndex such that the value of the dp array changes first.
                    }
                    actualSellDay = sellIndex + 1;
                    actualBuyDay = lmin[sellIndex] + 1;
                }

            }
            System.out.println("The maximum stock is "+stock+". The Profit is "+maxProfit +". The buy day is "+actualBuyDay+". The sell day is "+actualSellDay);
        }
        catch(Exception e){

        }
        finally{
            in.close();
        }

    }

    public static int maxProf(int[] price, int[] lmin, int j, int sellIndex, int[] dp){
        if(dp[j]!= -1){
            return dp[j];
        }

        if(j==0){
            dp[j] = 0;
            return dp[j];
        }
        int bef = maxProf(price, lmin, j-1, sellIndex, dp);
        if(price[j] - price[lmin[j-1]] > bef){
            dp[j] = price[j] - price[lmin[j-1]];
            sellIndex = j;
        }
        else{
            dp[j] = bef;
        }

        return dp[j];
    }
}
