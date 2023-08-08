import java.util.Scanner;

class Bucket
{
    boolean full=false;
    int n;
    synchronized void get(int x)
    {
        if(full==true)
        {
            try
            {
                Thread.sleep(200);
                wait();
            }
            catch(Exception e){}
        }
        n=x;
        System.out.println("Produced : "+n);
        full=true;
        notify();
    }
    synchronized void put()
    {
        if(full==false)
        {
            try
            {
                Thread.sleep(200);
                wait();
            }
            catch(Exception e){}   
        } 
        System.out.println("Consumed : "+n);
        full=false;
        notify();
    }
}

class Consumer extends Thread
{
    Bucket b;
    Consumer(Bucket b)
    {
        this.b=b;
    }
    public void run()
    {
        for(int i=1;i<=10;i++)
            b.put();
    }
}
class Producer extends Thread
{
    Bucket b;
    Producer(Bucket b)
    {
        this.b=b;
    }
    public void run()
    {
        for(int i=1;i<=10;i++)
            b.get(i);
    }
}

public class InterThreadCommunication
{
    public static void main(String  args[])
    {
        Bucket b =new Bucket();
        Consumer c = new Consumer(b);
        Producer p = new Producer(b);
        p.start();
        c.start();
    }   
}