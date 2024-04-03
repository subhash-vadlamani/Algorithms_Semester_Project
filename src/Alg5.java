package src;
import java.util.*;
public class Alg5 {
    public static void main(String []args)
    {

        int m, n, i, j, k;
        int finalBuyDay = 0;
        int finalSellDay = 0;

        Scanner in = null;
        System.out.println("Enter the value of 'k', the number of transactions allowed: ");
        in = new Scanner(System.in);
        k = in.nextInt();

        System.out.println("Enter the number of stocks 'm' and the number of days 'n'.");

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


//        int k = 3;
//        int[][] price = { {8,1,13,4,5 }, { 6, 12, 8, 17, 13}, {9, 8, 6, 4, 7}, {1, 4, 3, 2,1} };
//        int n = price[0].length;
//        int m = price.length;
        int[][] profit = new int[k+1][n];
        // Here, profit[i][j] represents the maximum profit that is achieved for upto 'i' transactions, till day 'j'.
        int selling =0;

        for (i = 0; i <= k; i++)
            profit[i][0] = 0;
            // Here, we do this because, maximum profit that is achieved at day 0, given however many transactions is 0.


        for (j = 0; j < n; j++)
            profit[0][j] = 0;
            // Here, we do this because the maximum profit that can be achieved for all days, given 0 transactions to work with is
            // always 0

        for (int l = 1; l <= k; l++)
        {
            for ( j = 1; j < n; j++)
            {
                int maxprofit = 0;
                for (int a = 0; a < j; a++)
                {
                    for( i=0;i<stockPriceMatrix.length;i++) {
                        if(maxprofit < (stockPriceMatrix[i][j] - stockPriceMatrix[i][a] + profit[l - 1][a])){
                            // We enter here, if we get more profit while considering i'th stock on jth day
                            maxprofit = stockPriceMatrix[i][j] - stockPriceMatrix[i][a] + profit[l - 1][a];
                        }
                        profit[l][j] = Math.max(profit[l][j - 1],
                                maxprofit);
                        // We keep on updating the profit matrix so that we can keep track of the
                        // profit that can be achieved till transaction 'l' and day 'j'
                    }
                }
            }
        }
        System.out.println("The profit matrix is as follows : ");
        for(int a = 0; a <= k; a++){
            for(int b = 0; b< n ;b++){
                System.out.printf("%4d", profit[a][b]);
            }
            System.out.print("\n");
        }
        System.out.println("Maximum Profit is: "+profit[k][n - 1]);



        i=n-1;
        int currentTransaction=0;
        while(currentTransaction<k && currentTransaction>=0 && i>0){
            while(profit[k][i]==profit[k][i-1]){
                i=i-1;
            }
            // We stop at the index where the profit changes.
            System.out.print("\nSell on day: "+i);
            int[] firstDifference = new int[m];
            int[] secondDifference = new int[m];

            // Here, we consider the day on which we sell to find the diff
            for(int x=0; x<m; x++){
                firstDifference[x] = profit[k][i]-stockPriceMatrix[x][i];
            }

            // This is the difference that can be achieved with one less transactions and one less day
            for(int y=0; y<m; y++){
                secondDifference[y]=profit[k-1][i-1]-stockPriceMatrix[y][i-1];
            }
            int flag=0;
            for (int z = 0; z < m; z++) {
                if (firstDifference[z] == secondDifference[z]) {
                    int b = i-1;
                    finalBuyDay = b + 1;
                    finalSellDay = z + 1;

                    System.out.print(" Buy day:" + (finalBuyDay)+ " Stock is " + finalSellDay);
                    flag=1;
                    break;

                }
            }
            int a=i-1;
            while(flag==0){
                a=a-1;
                for(int y=0; y<m; y++){
                    secondDifference[y]=profit[k-1][a]-stockPriceMatrix[y][a];
                }
                for (int z = 0; z < m; z++) {
                    if (firstDifference[z] == secondDifference[z]) {
                        finalBuyDay = a + 1;
                        finalSellDay = z + 1;

                        System.out.print(" Buy day is" + (finalBuyDay)+ " Stock is " + finalSellDay);
                        flag=1;
                    }
                }
            }
            currentTransaction=currentTransaction+1;
            i=i-1;
        }
    }
}

