package lab7;

public class DepositThread implements Runnable {

  private Account account;
  private double money;
  private int flag;

  public DepositThread(Account account, double money, int status) {
    this.account = account;
    this.money = money;
    this.flag = status;
  }

  @Override
  public void run() {
    if (flag == 0) {
      account.deposit0(money);
    } else if (flag == 1) {
      account.deposit1(money);
    }
  }
}
