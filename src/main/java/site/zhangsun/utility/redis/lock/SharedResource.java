package site.zhangsun.utility.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Functions: 多线程环境下的共享资源对象.
 *
 * @author zhangsunjiankun
 * @date 2020/8/1 下午10:27
 * @since 1.0
 */
@Slf4j
@Service
public class SharedResource {

    @Resource
    private RedisDistributedLock redisDistributedLock;

    public SharedResource(int stock) {
        this.stock = stock;
    }

    public SharedResource() {
        this.stock = 10;
    }

    /**
     * 库存余量
     */
    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * 扣件库存
     *
     * 方式一：同步代码快
     */
    public void decreaseStock1() {
        long currentThreadId = Thread.currentThread().getId();
        String currentThreadName = Thread.currentThread().getName();
        log.info("Current Thread ID: {}, Name: {}, Stock: {}", currentThreadId, currentThreadName, stock);
        // 减库存
        if (stock <= 0) {
            log.warn("库存不足, {}, ", stock);
            return;
        }

        // 此处非原子性操作
        synchronized(this) {
            stock--;
            log.info("Decrease Stock Done: {}", stock);
        }

        if (stock < 0) {
            log.error("库存超卖，出现线程安全问题，库存： {}, 当前线程ID：{}", stock, Thread.currentThread().getId());
        }
    }

    /**
     * 扣件库存
     *
     * 方式二：同步方法
     */
    public synchronized void decreaseStock2() {
        long currentThreadId = Thread.currentThread().getId();
        String currentThreadName = Thread.currentThread().getName();
        log.info("Current Thread ID: {}, Name: {}, Stock: {}", currentThreadId, currentThreadName, stock);
        // 减库存
        if (stock <= 0) {
            log.warn("库存不足, {}, ", stock);
            return;
        }

        // 此处非原子性操作
        stock--;
        log.info("Decrease Stock Done: {}", stock);

        if (stock < 0) {
            log.error("库存超卖，出现线程安全问题，库存： {}, 当前线程ID：{}", stock, Thread.currentThread().getId());
        }
    }

    /**
     * 扣件库存
     *
     * 方式三：分布式锁
     */
    public void decreaseStock3() {
        redisDistributedLock.lock();
        try {
            long currentThreadId = Thread.currentThread().getId();
            String currentThreadName = Thread.currentThread().getName();
            log.info("Current Thread ID: {}, Name: {}, Stock: {}", currentThreadId, currentThreadName, stock);
            // 减库存
            if (stock <= 0) {
                log.warn("库存不足, {}, ", stock);
                return;
            }
            // 此处非原子性操作
            stock--;
            log.info("Decrease Stock Done: {}", stock);

            if (stock < 0) {
                log.error("库存超卖，出现线程安全问题，库存： {}, 当前线程ID：{}", stock, Thread.currentThread().getId());
            }
        } finally {
            redisDistributedLock.unlock();
        }
    }
}
