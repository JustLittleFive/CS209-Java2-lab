package lab7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Test {
  @State(Scope.Thread)
  public static class myStatus{
    Account account;

    @Setup(Level.Iteration)
    public void setUp(){
      account = new Account();
    }
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public static void testSynchronized() {
    Account account = new Account();
    ExecutorService service = Executors.newFixedThreadPool(100);

    for (int i = 1; i <= 100; i++) {
      service.execute(new DepositThread(account, 10, 0));
    }

    service.shutdown();

    while (!service.isTerminated()) {}

    System.out.println("Balance: " + account.getBalance());
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public static void testReentrantLock() {
    Account account = new Account();
    ExecutorService service = Executors.newFixedThreadPool(100);

    for (int i = 1; i <= 100; i++) {
      service.execute(new DepositThread(account, 10, 1));
    }

    service.shutdown();

    while (!service.isTerminated()) {}

    System.out.println("Balance: " + account.getBalance());
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(Test.class.getSimpleName())
        .measurementIterations(3)
        .warmupIterations(1)
        .mode(Mode.AverageTime)
        .forks(1)
        .shouldDoGC(true)
        .build();
    new Runner(options).run();
  }
}
