package site.zhangsun.utility.redis.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Functions: 测试入口.
 *
 * @author zhangsunjiankun
 * @date 2020/8/2 上午10:48
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {
        SharedResource resource = new SharedResource(1);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 10; i++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        resource.decreaseStock2();
                    }
                });
            }
        } finally {
            executorService.shutdown();
        }

    }
}
