package core.thread.pc;

import java.util.Vector;

class Producer extends Thread {
 
    static final int MAXQUEUE = 10;
    private Vector<String> messages = new Vector<String>();
    
    //private AtomicLong quantity;
 
    @Override
    public void run() {
        try {
            while (true) {
                putMessage();
                sleep(500);
            }
        } catch (InterruptedException e) {
        }
    }
 
    private synchronized void putMessage() throws InterruptedException {
        while (messages.size() == MAXQUEUE) {
            wait();
        }
        String msg = new java.util.Date().toString()+"-"+new java.util.Date().getTime();
        messages.addElement(msg);
        System.out.println("Put message:"+ msg);
        notify();
        //Later, when the necessary event happens, the thread that is running it calls notify() from a block synchronized on the same object.
    }
 
    // Called by Consumer
    public synchronized String getMessage() throws InterruptedException {
        notify();
        
        String message = "No message";
        
        while (messages.size() == 0 && messages.size() <= 5) {
            wait();//By executing wait() from a synchronized block, a thread gives up its hold on the lock and goes to sleep.
        }
        int i = 0;
        while(messages.size() >= 3 ) {
        	message = messages.firstElement();
        	++i;
        	System.out.println("Removendo mensagem "+i+") "+ message);
            messages.removeElement(message);
        }
        
        return message;
    }
}
 
class Consumer extends Thread {
 
    Producer producer;
 
    Consumer(Producer p) {
        producer = p;
    }
 
    @Override
    public void run() {
        try {
            while (true) {
                String message = producer.getMessage();
                System.out.println("Got message: " + message);
                sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String args[]) {
        Producer producer = new Producer();
        producer.start();
        new Consumer(producer).start();
    }
}