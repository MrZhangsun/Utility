package site.zhangsun.utility.redis.lock;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import site.zhangsun.utility.UtilityApplicationTests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedResourceTest  extends UtilityApplicationTests {

    @Autowired
    private SharedResource sharedResource;

    @Test
    public void testDecreaseStock3() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 10; i++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        sharedResource.decreaseStock3();
                    }
                });
            }
        } finally {
            executorService.shutdown();
        }
    }
}