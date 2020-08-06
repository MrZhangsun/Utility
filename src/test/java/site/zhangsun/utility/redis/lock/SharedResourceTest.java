package site.zhangsun.utility.redis.lock;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import site.zhangsun.utility.UtilityApplicationTests;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedResourceTest  extends UtilityApplicationTests {

    @Autowired
    private SharedResource sharedResource;

    @Test
    public void testDecreaseStock3() {
        ExecutorService executorService = Executors.newFixedThreadPool(75);
        try {
            for (int i = 0; i < 20000; i++) {
                int finalI = i;
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("第" + finalI + "次执行");
                        sharedResource.decreaseStock3();
                    }
                });
            }

            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}