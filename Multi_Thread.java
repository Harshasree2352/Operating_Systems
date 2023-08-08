import java.util.Scanner;
class Tickets
{
    static int x;
    Tickets(int x)
    {
        Tickets.x=x;
    }
    synchronized void book()
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter no of tickets you want : ");
        int n=sc.nextInt();
        while(n>x)
        {
            System.out.println("Only "+x+" tickets available.\nDo you want to continue (y/n) : ");
            if( sc.next().charAt(0)=='y' )
            {
                System.out.print("Enter no of tickets you want : ");
                n=sc.nextInt();
            }
               
            else
            {
                System.out.println("Transaction over.");
                return;
            }
        }
        System.out.println(n+" tickets are booked for the customer.");
        x-=n;
        System.out.println("Transaction over.");
    }
}
class PersonA extends Thread
{
    Tickets t;
    PersonA(Tickets t)
    {
        this.t=t;
    }
    public void run()
    {
        t.book();
    }
}
class PersonB extends Thread
{
    Tickets t;
    PersonB(Tickets t)
    {
        this.t=t;
    }
    public void run()
    {
        t.book();
    }
}
public class Multi_Thread
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no of tickets : ");
        Tickets t = new Tickets(sc.nextInt());
        PersonA pa = new PersonA(t);
        PersonB pb = new PersonB(t);
        pa.start();
        pb.start();
    }
}