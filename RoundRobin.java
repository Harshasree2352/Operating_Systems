import java.util.Scanner;
public class RoundRobin
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no.of processes : ");
        int n = sc.nextInt();
        int bursttime[] = new int[n];
        int temp[] = new int[n];
        int process[] = new int[n];
        int completiontime[] = new int[n];
        for(int i=0;i<n;i++)
        {
            System.out.print("Enter burst time of process "+(i+1)+" : ");
            temp[i]=bursttime[i]=sc.nextInt();
            process[i]=i+1;
        }
        System.out.print("Enter quantum time : ");
        int quantumtime = sc.nextInt();
        int c=0,i=0;
        while(c!=n)
        {   
            if( bursttime[i]>0 )
            {
                int time;
                if(bursttime[i]>quantumtime)
                {
                    bursttime[i]-=quantumtime;
                    time=quantumtime;
                    for(int j=0;j<n;j++)
                        if(bursttime[j]!=0)
                            completiontime[j]+=time;
                }
                else
                {
                    time=bursttime[i];
                    for(int j=0;j<n;j++)
                        if(bursttime[j]!=0)
                            completiontime[j]+=time;
                    bursttime[i]=0;
                    c++;
                }
            }
            i = (i+1)%n;
        }
        int ttat=0;
        System.out.println("Burst Time\t\tCompletion Time\tTurn Around Time");
        for(i=0;i<n;i++)
        {
            System.out.println(temp[i]+"\t\t"+completiontime[i]+"\t\t"+(completiontime[i]-bursttime[i]));
            ttat+=(completiontime[i]-bursttime[i]);
        }
        System.out.println("Average Turn around time : "+(ttat/n));
    }
}