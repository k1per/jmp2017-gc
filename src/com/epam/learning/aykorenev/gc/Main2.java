package com.epam.learning.aykorenev.gc;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by k1per on 20.06.17.
 * 1)Serial collector -XX:+UseSerialGC -Xms6m -Xmx18m -XX:NewSize=2m -XX:MaxMetaspaceSize=30m
 * 2)Parallel -XX:+UseParallelGC -XX:NewSize=1m -Xms3m -Xmx12m -XX:MaxMetaspaceSize=20m
 * 3)ParallelOldGc -XX:+UseParallelOldGC -Xms9m -Xmx18m -XX:NewSize=3m -XX:MaxMetaspaceSize=40m
 * 4)ConcuMarkSweep -XX:+UseConcMarkSweepGC -XX:ConcGCThreads=2 -Xms6m -Xmx18m -XX:NewSize=1m -XX:MaxMetaspaceSize=30m
 * 5)G1 -XX:+UseG1GC -Xms4m -Xmx16m -XX:NewSize=2m -XX:MaxMetaspaceSize=18m
 */
public class Main2 {
    public static void main(String[] args) throws InterruptedException {

        //Start and connect to VisualVm
        Thread.sleep(20_000);
        System.out.println("Started");
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(20_000_000, 300_000_000); i++) {
            ArrayList<Integer> integers = new ArrayList<>(ThreadLocalRandom.current().nextInt(1000, 10_000));
            for (int k = 0; k < integers.size(); k++) {
                integers.add(ThreadLocalRandom.current().nextInt(100));
            }
            Thread.sleep(10);
            if (ThreadLocalRandom.current().nextInt(3) % 3 != 0) {
                integers = null;
            }
        }
        Thread.sleep(10_000);
    }
}
