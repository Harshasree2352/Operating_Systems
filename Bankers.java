import java.util.Scanner;
public class Bankers
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n : ");
        int n=sc.nextInt();
        System.out.print("Enter resources (m) : ");
        int m=sc.nextInt();
        int allo[][] = new int[n][m];
        int max[][] = new int[n][m];
        int need[][] = new int[n][m];
        int ava[] = new int[m];
        boolean visit[] = new boolean[n];
        int count=0;
        System.out.println("Enter allocation matrix  : ");
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                allo[i][j]=sc.nextInt();
        System.out.println("Enter max matrix  : ");
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
            {
                max[i][j]=sc.nextInt();
                need[i][j]=max[i][j]-allo[i][j];
            }
                
        System.out.println("Enter available  : ");
        for(int i=0;i<m;i++)
                ava[i]=sc.nextInt();
        int seq[] = new int[n];
        while(count<n)
        {
            boolean flag=false;
            for(int i=0;i<n;i++)
            {
                if(visit[i]==false)
                {
                    int j;
                    for(j=0;j<m;j++)
                        if(need[i][j] > ava[j])break;
                    if(j==m)
                    {
                        seq[count++]=i+1;
                        visit[i]=true;
                        flag=true;
                        for(j=0;j<m;j++)
                            ava[j]+=need[i][j];
                    }
                }
            }
            if(flag==false)break;
        }
        int i=0;
        if(count<n)
            System.out.println("Not in safe state");
        else 
            for(System.out.println("Safe state is :"), i=0 ;i<n;i++)
                System.out.print(seq[i]+"  ");
    }
}