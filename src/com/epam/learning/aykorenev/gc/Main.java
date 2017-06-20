package com.epam.learning.aykorenev.gc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by k1per on 20.06.17.
 * 1)Serial collector -XX:+UseSerialGC -Xms6m -Xmx18m -XX:NewSize=2m -XX:MaxMetaspaceSize=30m
 * 2)Parallel -XX:+UseParallelGC -XX:NewSize=1m -Xms3m -Xmx12m -XX:MaxMetaspaceSize=20m
 * 3)ParallelOldGc -XX:+UseParallelOldGC -Xms9m -Xmx18m -XX:NewSize=3m -XX:MaxMetaspaceSize=40m
 * 4)ConcuMarkSweep -XX:+UseConcMarkSweepGC -XX:ConcGCThreads=2 -Xms6m -Xmx18m -XX:NewSize=1m -XX:MaxMetaspaceSize=30m
 * 5)G1 -XX:+UseG1GC -Xms4m -Xmx16m -XX:NewSize=2m -XX:MaxMetaspaceSize=18m
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        //Start and connect to VisualVm
        Thread.sleep(20_000);
        System.out.println("Started");

        List<List<Integer>> listOfListNumbers = new ArrayList<>();

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(10_000_000); i++) {
            List<Integer> listOfNumbers = new ArrayList<>(ThreadLocalRandom.current().nextInt(100_000));
            for (int k = 0; k < listOfNumbers.size(); k++) {
                listOfNumbers.add(ThreadLocalRandom.current().nextInt(2_000_000));
            }
            listOfListNumbers.add(new ArrayList<>(ThreadLocalRandom.current().nextInt(10)));
            if (ThreadLocalRandom.current().nextInt(13) % 3 == 0) {
                for (int j = 0; j < listOfListNumbers.size() / 3; j++) {
                    listOfListNumbers.set(ThreadLocalRandom.current().nextInt(listOfListNumbers.size()), null);
                }
            }
        }
        Thread.sleep(3000);
        System.out.println("Release the pointer");
        listOfListNumbers = null;
        Thread.sleep(60_000);
    }
}
