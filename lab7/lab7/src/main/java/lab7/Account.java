package lab7;

import java.util.concurrent.locks.ReentrantLock;

public class Account {

  private double balance;

  private final ReentrantLock lock = new ReentrantLock();

  /** using synchronized keyword
   *
   * @param money
   */
  public synchronized void deposit0(double money) {
    try {
      double newBalance = balance + money;
      try {
        Thread.sleep(10); // Simulating this service takes some processing time
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      balance = newBalance;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /** using ReentrantLock
   *
   * @param money
   */
  public void deposit1(double money) {
    lock.lock();
    try {
      double newBalance = balance + money;
      try {
        Thread.sleep(10); // Simulating this service takes some processing time
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      balance = newBalance;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public double getBalance() {
    return balance;
  }
}
