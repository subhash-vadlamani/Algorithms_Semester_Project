package src;
import java.util.*;
class DP6b {
    public static void main(String []args)
    {

        int m, n, i, j, k;


        Scanner in = new Scanner(System.in);
        System.out.println("Enter the value of 'k', the maximum transactions that are allowed.");
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



        int[][] profit = new int[k+1][n];;
        int selling =0;

        for ( i = 0; i <= k; i++)
            profit[i][0] = 0;
            // This means that however many transactions were give, it is not possible to make a profit on 0th day.


        for ( j = 0; j < n; j++)
            profit[0][j] = 0;
            // This means that the maximum profit that can be achieved for all the days, given no transactions is 0.

        int [] diff = new int[m];
        Arrays.fill(diff, -1000);

        for( i=1;i<=k;i++) {
            for( j=1;j<n;j++) {
                int maxx=-1000;
                if(i>j)
                    continue;

                for(int l=0;l<m;l++) {
                    diff[l]=Math.max(diff[l],profit[i-1][j-1]-stockPriceMatrix[l][j-1]);
                    maxx=Math.max(maxx, stockPriceMatrix[l][j]+diff[l]);

                }
                profit[i][j]=Math.max(profit[i][j-1], maxx);
            }
        }
        for(int a = 0; a <= k; a++){
            for(int b = 0; b< n ;b++){
                System.out.printf("%4d", profit[a][b]);
            }
            System.out.print("\n");
        }
        System.out.println("Maximum Profit is: "+profit[k][n - 1]);

        i = profit.length - 1;
        j = profit[0].length - 1;
        Deque<Integer> current = new LinkedList<>();
        Deque<Integer> stock_ind = new LinkedList<>();
        while(true && i!=0 && j!=0) {
            if (profit[i][j] == profit[i][j-1]) {
                j = j - 1;
            } else {
                current.addFirst(j);

                int maxDiff[] = new int[stockPriceMatrix.length];
                for (int l = 0; l < stockPriceMatrix.length; l++)
                {
                    maxDiff[l] = profit[i][j] - stockPriceMatrix[l][j];
                }
                int flag = 0;
                for (int c = j-1; c >= 0; c--)
                {
                    for (int p = 0; p < stockPriceMatrix.length; p++)
                    {
                        for(int l = 0; l< stockPriceMatrix.length; l++)
                        {
                            if ((profit[i-1][c] - stockPriceMatrix[p][c] == maxDiff[l]) && ((profit[i-1][c] + stockPriceMatrix[p][j]- stockPriceMatrix[p][c])==profit[i][j])) {
                                i = i - 1;
                                j = c;
                                current.addFirst(j);
                                stock_ind.addFirst(l);
                                flag = 1;
                                break;
                            }
                        }
                        if( flag == 1)
                            break;
                    }
                    if( flag == 1)
                        break;
                }

            }
        }

        while(!current.isEmpty()) {
            int st_ind = stock_ind.pollFirst();
            System.out.println((st_ind)+ " "+(current.pollFirst())+" "+(current.pollFirst()));
        }
    }
}