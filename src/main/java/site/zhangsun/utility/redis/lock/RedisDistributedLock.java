package site.zhangsun.utility.redis.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import site.zhangsun.utility.exception.AcquireLockTimeoutException;
import site.zhangsun.utility.redis.RedisUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Functions: 基于Redis实现分布式锁.
 *
 * 注意事项：
 * 1. 锁释放只能是加锁线程
 * 2. 加锁和超时时间的设置必须是原子性操作
 * 3. 锁过期时要判断是否是异常情况，如果是正常情况，那么就需要延长锁过期时间
 * 
 *
 *
 * @author zhangsunjiankun
 * @date 2020/8/1 下午10:26
 * @since 1.0
 */
@Component
public class RedisDistributedLock implements Lock {

    /**
     * 分布式锁key标识
     */
    private static final String DISTRIBUTED_LOCK_KEY = "LOCK:DISTRIBUTED_LOCKER";

    /**
     * 默认锁超时时间为10秒，防止死锁的超时时间
     */
    private long defaultLockedTimeoutMillis;
    /**
     * 默认获取锁超时时间为5秒，即获取锁等待超时时间
     */
    private long defaultAcquireTimeoutMillis;

    /**
     * Redis数据库操作工具类
     */
    private RedisUtil redisUtil;

    @Autowired
    public RedisDistributedLock(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
        this.defaultAcquireTimeoutMillis = 50000;
        this.defaultLockedTimeoutMillis = 60000;
    }

    public RedisDistributedLock(long acquireTimeout, TimeUnit acquireTimeUnit,
                                long lockedTimeout, TimeUnit lockedTimeUnit) {
        this.defaultAcquireTimeoutMillis = acquireTimeUnit.convert(acquireTimeout, TimeUnit.MILLISECONDS);
        this.defaultLockedTimeoutMillis = lockedTimeUnit.convert(lockedTimeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 加锁，并设置超时时间（支持中断获取锁）
     *
     * 如果该锁不可用，当前线程会自旋获取锁，直到获得该锁为止。这个过程中忽略当前线程的中断状态
     */
    @Override
    public void lock() {
        long currentThreadId = Thread.currentThread().getId();
        long timeout = System.currentTimeMillis() + defaultAcquireTimeoutMillis;
        // 如果加锁成功，则什么事情都不用做, 如果加锁失败，则自旋获取锁，直到加锁成功为止
        while (!tryLock() && timeout > System.currentTimeMillis()) {
            try {
                // 自旋200毫秒
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 获取锁超时
        if (timeout <= System.currentTimeMillis()) {
            throw new AcquireLockTimeoutException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "获取Redis锁超时",
                    String.format("超时时间: %d毫秒, 超时线程：%s", defaultAcquireTimeoutMillis, currentThreadId));
        }
        System.out.println("线程" + currentThreadId + "获取到了锁+");
    }

    /**
     * 尝试获取锁，如果当前线程在获取锁的过程中被其他线程中断，那么就停止继续自旋获取锁，直接抛出中断异常即可
     *
     * 如果加锁成功，就直接返回
     * 如果锁被占用，则线程一直自旋获取锁，直到出现一下两种情况：
     * 1. 成功加锁
     * 2. 当前线程被其他线程中断，此时抛出线程中断异常
     *
     * @throws InterruptedException 当前线程被其他线程中断，此时抛出线程中断异常
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        // 如果加锁成功，则什么事情都不用做, 如果加锁失败，则自旋获取锁，直到加锁成功为止
        while (!tryLock()) {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            // 自旋200毫秒, 此时如果在尝试获取锁的过程中当前线程被其他线程中断了, 直接抛出异常，停止获取锁
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }

    /**
     * 尝试获取锁，如果锁空闲，直接加锁。否则，直接返回，不会重复尝试获取锁
     *
     * 加锁成功返回true，失败，返回false
     *
     * 这个方法典型的使用场景如下：
     * Lock lock = new DistributedLock();
     * if (lock.tryLock()) {
     *   try {
     *     // 操纵保护状态
     *   } finally {
     *     // 解锁
     *     lock.unlock();
     *   }
     * } else {
     *   // 执行其他为加锁的代码
     * }}
     */
    @Override
    public boolean tryLock() {
        long currentThreadId = Thread.currentThread().getId();
        return redisUtil.setIfAbsent(DISTRIBUTED_LOCK_KEY, String.valueOf(currentThreadId),
                defaultLockedTimeoutMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * 在当前的线程不被打断的情况下，当前线程会在给定的超时时间内不断尝试获取锁
     *
     * @param time 获取锁超时时间
     * @param unit 超时时间单位
     *
     * @return 如果加锁成功，立即回返回true
     *         如果锁被其他线程占用，当前线程会自旋获取锁，直到出现一下三种情况之一：
     *          1. 成功加锁后立即返回true;
     *          2. 当前线程被其他线程打断，抛出异常;
     *          3. 获取锁超时，直接返回false
     * @throws InterruptedException 当前线程在获取锁的过程中被其他线程打断
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long convertedMills = unit.convert(time, TimeUnit.MILLISECONDS);
        long timeout = System.currentTimeMillis() + convertedMills;
        // 如果加锁成功，则什么事情都不用做, 如果加锁失败，则自旋获取锁，直到加锁成功为止
        while (!tryLock() && timeout > System.currentTimeMillis()) {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            // 自旋200毫秒, 此时如果在尝试获取锁的过程中当前线程被其他线程中断了, 直接抛出异常，停止获取锁
            TimeUnit.MILLISECONDS.sleep(200);
        }
        // 获取锁超时
        return timeout > System.currentTimeMillis();
    }

    /**
     * Releases the lock.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>A {@code Lock} implementation will usually impose
     * restrictions on which thread can release a lock (typically only the
     * holder of the lock can release it) and may throw
     * an (unchecked) exception if the restriction is violated.
     * Any restrictions and the exception
     * type must be documented by that {@code Lock} implementation.
     */
    @Override
    public void unlock() {
        // 解锁只能是由加锁的线程解锁
        long currentThreadId = Thread.currentThread().getId();
        String savedThreadId = redisUtil.get(DISTRIBUTED_LOCK_KEY);
        if (!String.valueOf(currentThreadId).equals(savedThreadId)) {
            throw new IllegalThreadStateException("请勿尝试释放其他线程资源锁");
        }
        redisUtil.delete(DISTRIBUTED_LOCK_KEY);
        System.out.println("线程" + currentThreadId + "释放了锁-");
    }

    /**
     * Returns a new {@link Condition} instance that is bound to this
     * {@code Lock} instance.
     *
     * <p>Before waiting on the condition the lock must be held by the
     * current thread.
     * A call to {@link Condition#await()} will atomically release the lock
     * before waiting and re-acquire the lock before the wait returns.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The exact operation of the {@link Condition} instance depends on
     * the {@code Lock} implementation and must be documented by that
     * implementation.
     *
     * @return A new {@link Condition} instance for this {@code Lock} instance
     * @throws UnsupportedOperationException if this {@code Lock}
     *                                       implementation does not support conditions
     */
    @Override
    public Condition newCondition() {
        // TODO 实现Redis同步队列器
        return new RedisCondition().new ConditionObject();
    }

    public long getDefaultLockedTimeoutMillis() {
        return defaultLockedTimeoutMillis;
    }

    public void setDefaultLockedTimeoutMillis(int defaultLockedTimeoutMillis) {
        this.defaultLockedTimeoutMillis = defaultLockedTimeoutMillis;
    }

    public long getDefaultAcquireTimeoutMillis() {
        return defaultAcquireTimeoutMillis;
    }

    public void setDefaultAcquireTimeoutMillis(int defaultAcquireTimeoutMillis) {
        this.defaultAcquireTimeoutMillis = defaultAcquireTimeoutMillis;
    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

}
