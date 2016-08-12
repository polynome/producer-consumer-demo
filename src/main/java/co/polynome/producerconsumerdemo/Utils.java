package co.polynome.producerconsumerdemo;

import java.util.Random;

public class Utils {
  private static final float RANDOM_KILL_RATE = 0.15f;
  private static final int RANDOM_SLEEP_DELAY_SECONDS = 5;

  private static final Random random = new Random();

  public static void failRandomly(final String callerName) {
    if (random.nextFloat() < RANDOM_KILL_RATE) {
      throw new RuntimeException(callerName + " has randomly died!");
    }
  }

  public static void randomSleep() {
    try {
      Thread.sleep((long)random.nextInt(RANDOM_SLEEP_DELAY_SECONDS * 1000));
    } catch (InterruptedException e) {
      System.out.println("Sleeper.randomSleep was interrupted!");
      throw new RuntimeException(e);
    }
  }
}
