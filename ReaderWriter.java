import java.util.Scanner;
import java.util.concurrent.Semaphore;
public class ReaderWriter
{
    static Semaphore write = new Semaphore(1);
    static Semaphore read = new Semaphore(1);
    static int rc=0;
    static class Reader extends Thread
    {
        Reader(String s)
        {
            this.setName(s);
        }
        public void run()
        {
            try
            {
                read.acquire();
                rc++;
                if(rc==1)
                    write.acquire();
                read.release();
                System.out.println("Reader - "+Thread.currentThread().getName());
                Thread.sleep(500);
                rc--;
                System.out.println("Finished reading Reader - "+Thread.currentThread().getName());
                if(rc==0)
                    write.release();
            }
            catch(Exception e){}
        }
    }
    static class Writer extends Thread
    {
        Writer(String s)
        {
            Thread.currentThread().setName(s);
        }
        public void run()
        {
            try
            {
                write.acquire();
                System.out.println("Writer - "+Thread.currentThread().getName());
                try{
                    Thread.sleep(500);
                }
                catch(Exception e){}
                System.out.println("Finished writing");
                write.release();
            }
            catch(Exception e){}
        }
    }
    public static void main(String args[])
    {
        Reader r1 = new Reader("1");
        Reader r2 = new Reader("2");
        Reader r3 = new Reader("3");
        Reader r4 = new Reader("4");
        Writer w1 = new Writer("1");
        Writer w2 = new Writer("2");
        w2.start();
        w1.start();
        r1.start();
        r2.start();
        r3.start();
        r4.start();
    }
}