package practice2.lab2;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Practice2 {

  @State(Scope.Thread)
  public static class MyState {

    int[] arrayImpl;
    List<Integer> arraylistImpl;
    List<Integer> linkedlistImpl;
    HashMap<Integer, Integer> intmapImpl;

    int LENGTH = 1000;
    int OFFSET = 12010001;

    int index;

    @Setup(Level.Iteration)
    public void setUp() {
      arrayImpl = new int[LENGTH];
      arraylistImpl = new ArrayList<>();
      linkedlistImpl = new LinkedList<>();
      intmapImpl = new HashMap<>();

      index = new Random().nextInt(LENGTH) + OFFSET;

      for (int i = OFFSET; i < OFFSET + LENGTH; i++) {
        int age = new Random().nextInt(5) + 18;
        intmapImpl.put(i, age);
        // TODO: initialize arrayImpl, arraylistImpl and linkedlistImpl
        arrayImpl[i - OFFSET] = age;
        arraylistImpl.add(age);
        linkedlistImpl.add(age);
      }
    }
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public static int testintmap(Practice2.MyState state) {
    return state.intmapImpl.get(state.index);
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public static int testarraylist(Practice2.MyState state) {
    // TODO return the age by state.index
    return state.arraylistImpl.get(state.index - state.OFFSET);
    // return 0;
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public static int testlinkedlist(Practice2.MyState state) {
    // TODO return the age by state.index
    return state.linkedlistImpl.get(state.index - state.OFFSET);
    // return 0;
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public static int testarray(Practice2.MyState state) {
    // TODO return the age by state.index
    return state.arrayImpl[state.index - state.OFFSET];
    // return 0;
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(Practice2.class.getSimpleName())
        .measurementIterations(3)
        .warmupIterations(1)
        .mode(Mode.AverageTime)
        .forks(1)
        .shouldDoGC(true)
        .build();
    new Runner(options).run();
  }
}
