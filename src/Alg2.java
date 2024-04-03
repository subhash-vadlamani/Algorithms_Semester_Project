package src;
import java.util.Scanner;

public class Alg2 {
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
            int sellDay=0,buyDay=0;

            for(i=0;i<m;i++){
                currentStock = i + i;
                // Initially, we consider the buyPrice to be the first day's price
                buyPrice = stockPriceMatrix[i][0];
                buyDay = 1;
                for(j=1;j<n;j++){
                    if (buyPrice > stockPriceMatrix[i][j]){
                            buyPrice = stockPriceMatrix[i][j];
                            // We keep reducing the buyPrice if we find a price that is less than the previously min
                            // buy price
                            buyDay = j + 1;
                    }
                    else if (stockPriceMatrix[i][j] - buyPrice > maxProfit){
                        // If the current profit that can be achieved is more than the max profit that has been achieved so far
                        // we store the current profit in the maxProfit variable and make the respective variable changes
                        // which are used to identify the stock, buy day and the sell day of that stock.
                        maxProfitStock = currentStock;
                        maxProfit = stockPriceMatrix[i][j];
                        stockBuyDay = buyDay;
                        stockSellDay = j + 1;

                    }
                }

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