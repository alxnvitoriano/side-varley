import java.util.concurrent.Semaphore;

public class LeitoresEscritores {
    private Semaphore mutex = new Semaphore(1); 
    private Semaphore db = new Semaphore(1);    
    private int rc = 0;                        
    public void reader() {
        while (true) {
            mutex.acquireUninterruptibly(); 
            rc++;
            if (rc == 1) {
                db.acquireUninterruptibly(); 
            }
            mutex.release(); 
            
            mutex.acquireUninterruptibly(); 
            rc--;
            if (rc == 0) {
                db.release(); 
            }
            mutex.release();
        }
    }

    public void writer() {
        while (true) {

            db.acquireUninterruptibly(); 
            
            db.release();
        }
    }
}