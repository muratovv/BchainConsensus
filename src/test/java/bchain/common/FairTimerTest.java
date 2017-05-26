package bchain.common;

import deferred_queue.core.Delay;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Timer tests and also use cases
 */
public class FairTimerTest {

    private Timer timer;

    @Before
    public void setUp() throws Exception {
        timer = new FairTimer();
        timer.setTime(Delay.delay(1, TimeUnit.SECONDS));
    }

    @Test
    public void start() throws Exception {
        final int[] sharedVariable = {0};
        timer.setDelegate(() -> sharedVariable[0] += 1);
        timer.start();
        Thread.sleep(100);
        Assert.assertEquals(0, sharedVariable[0]);
        Thread.sleep(910);
        Assert.assertEquals(1, sharedVariable[0]);
    }

    @Test
    public void deny() throws Exception {
        final int[] sharedVariable = {0};
        timer.setDelegate(() -> sharedVariable[0] += 1);
        timer.start();
        Thread.sleep(500);
        timer.deny();
        Thread.sleep(600);
        Assert.assertEquals(0, sharedVariable[0]);
    }

}