package ru.school.hogwarts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class task453 {

    private static final Logger logger = LoggerFactory.getLogger(task453.class);
    public static void main(String[] args) {

        long startTimeDefault = System.currentTimeMillis();
        logger.info("Was invoked method for calc value");
        int sumDefault = Stream.iterate(1, a -> a +1)
                .limit(1_000_000)
                .reduce(0, Integer::sum);
        long finishTimeDefault = System.currentTimeMillis() - startTimeDefault;
        System.out.println("sumDefault = " + sumDefault);
        logger.info("Working time = " + finishTimeDefault);

        long startTimeParallel = System.currentTimeMillis();
        logger.info("Was invoked method for calc value using parallel streams");
        int sumParallel = Stream.iterate(1, a -> a +1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, Integer::sum);
        long finishTimeParallel = System.currentTimeMillis() - startTimeParallel;
        System.out.println("sumParallel = " + sumParallel);
        logger.info("Working time = " + finishTimeParallel);

        long startTimeIntStreamParallel = System.currentTimeMillis();
        logger.info("Was invoked method for calc value using IntStream with parallel streams");
        int sumIntStreamParallel = IntStream.iterate(1, a -> a +1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, Integer::sum);
        long finishTimeIntStreamParallel = System.currentTimeMillis() - startTimeIntStreamParallel;
        System.out.println("sumIntStream = " + sumIntStreamParallel);
        logger.info("Working time = " + finishTimeIntStreamParallel);

        long startTimeIntStream = System.currentTimeMillis();
        logger.info("Was invoked method for calc value using IntStream");
        int sumIntStream = IntStream.iterate(1, a -> a +1)
                .limit(1_000_000)
                .reduce(0, Integer::sum);
        long finishTimeIntStream = System.currentTimeMillis() - startTimeIntStream;
        System.out.println("sumIntStream = " + sumIntStream);
        logger.info("Working time = " + finishTimeIntStream);

        long startTimeCycles = System.currentTimeMillis();
        logger.info("Was invoked method for calc value using cycles");
        int sumCycles = 0;
        for (int i = 1; i <= 1_000_000; i++) {
            sumCycles += i;
        }
        long finishTimeCycles = System.currentTimeMillis() - startTimeCycles;
        System.out.println("sumCycles = " + sumCycles);
        logger.info("Working time = " + finishTimeCycles);
    }
}
