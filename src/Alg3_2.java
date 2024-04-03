package src;
import java.util.Scanner;

public class Alg3_2{
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
                for(j=1;j<n;j++){
                    System.out.print(stockPriceMatrix[i][j] + " ");
                }
                System.out.println();
            }
            int maxProfit=0,buyPrice=0,currentStock=0,maxProfitStock=0;
            int stockSellDay=0,stockBuyDay=0;
            int sellDay=0, buyDay=0;

            for(i=0;i<m;i++){
                currentStock = i + 1;
                int[] dp = new int[n];
                // This array stores the maximum value that has been achieved till now.
                dp[0] = 0;
                int minPriceDay = 0;
                // This variable is used to store the day at which the minimum price of the stock has been encountered till now.

                for(j=1;j<n;j++){
                    if(stockPriceMatrix[i][j] < stockPriceMatrix[i][minPriceDay]){
                        minPriceDay = j;
                    }
                    if(stockPriceMatrix[i][j] - stockPriceMatrix[i][minPriceDay] > dp[j-1]){
                        dp[j] = stockPriceMatrix[i][j] - stockPriceMatrix[i][minPriceDay];
                        buyDay = minPriceDay;
                        sellDay = j;
                    }

                    else{
                        dp[j] = dp[j-1];
                        // If we are not able to achieve a higher profit with day j, then the maximum profit that has
                        //been achieved  till day 'j' is the same as the profit that can be achieved
                        // till day 'j-1'.
                    }
                }
                if(dp[n-1] > maxProfit){
                    maxProfit = dp[n-1];
                    maxProfitStock = currentStock; // stores the stock that will give us the maximum profit
                    stockBuyDay = buyDay; // stores the value of the day where we buy
                    stockSellDay = sellDay; // stores the value of the day where we sell the stock
                }

                // we check the max profit that can be achieved for stock 'i' is greater than the
                // maximum profit that has been achieved so far. If this is the case,
                // we are going to update the maxProfit variable

            }

            System.out.println("The most profitable stock, the max profit achieved so far, the stock buy day and the stock sell day are as follows:");
            System.out.println(maxProfitStock + " "+maxProfit + " " + stockBuyDay + " " + stockSellDay);

        }
        catch(Exception e){

        }
        finally{
            in.close();
        }
    }
}