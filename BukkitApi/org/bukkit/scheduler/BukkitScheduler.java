package org.bukkit.scheduler;

import org.bukkit.plugin.Plugin;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.List;

public interface BukkitScheduler {

    /**
     * 在指定延迟后调度一次任务。
     * <p>
     * 这个任务将由服务器主线程执行。
     * <p>
     * 原文：Schedules a once off task to occur after a delay.
     * <p>
     * This task will be executed by the main server thread.
     *
     * @param plugin 拥有这个任务的插件
     * @param task 要执行的任务
     * @param delay 服务器执行任务之前的延迟
     * @return 任务id（如果为-1则表示调度失败）
     */
    public int scheduleSyncDelayedTask(Plugin plugin, Runnable task, long delay);

    /**
     * @deprecated 推荐使用{@link BukkitRunnable#runTaskLater(Plugin, long)}
     * @param plugin 拥有这个任务的插件
     * @param task 要执行的任务
     * @param delay 服务器执行任务之前的延迟
     * @return 任务id（如果为-1则表示调度失败）
     */
    @Deprecated
    public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task, long delay);

    /**
     * 调度一次任务。
     * <p>
     * 这个任务将由服务器主线程执行。
     * <p>
     * 原文：Schedules a once off task to occur as soon as possible.
     * <p>
     * This task will be executed by the main server thread.
     *
     * @param plugin 拥有这个任务的插件
     * @param task 要执行的任务
     * @return 任务id（如果为-1则表示调度失败）
     */
    public int scheduleSyncDelayedTask(Plugin plugin, Runnable task);

    /**
     * @deprecated 推荐使用{@link BukkitRunnable#runTask(Plugin)}
     * @param plugin 拥有这个任务的插件
     * @param task 要执行的任务
     * @return 任务id（如果为-1则表示调度失败）
     */
    @Deprecated
    public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task);

    /**
     * 调度一个重复执行的任务。
     * <p>
     * 这个任务将由服务器主线程执行。
     * <p>
     * 原文：Schedules a repeating task.
     * <p>
     * This task will be executed by the main server thread.
     *
     * @param plugin 拥有这个任务的插件
     * @param task 要执行的任务
     * @param delay 服务器执行首次重复执行任务之后的延迟
     * @param period 任务执行的时间
     * @return 任务id（如果为-1则表示调度失败）
     */
    public int scheduleSyncRepeatingTask(Plugin plugin, Runnable task, long delay, long period);

    /**
     * @deprecated 推荐使用{@link BukkitRunnable#runTaskTimer(Plugin, long, long)}     *
     * @param plugin 拥有这个任务的插件
     * @param task 要执行的任务
     * @param delay 服务器执行首次重复执行任务之后的延迟
     * @param period 任务执行的时间
     * @return 任务id（如果为-1则表示调度失败）
     */
    @Deprecated
    public int scheduleSyncRepeatingTask(Plugin plugin, BukkitRunnable task, long delay, long period);

    /**
     * <b>异步任务应该从不访问任何Bukkit里的API。应着重保证异步任务的安全。</b>
     * <p>
     * 在指定延迟后调度一次任务。此任务将通过由调度器所管理的线程执行。
     * <p>
     * 原文：<b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules a once off task to occur after a delay. This task will be
     * executed by a thread managed by the scheduler.
     *
     * @param plugin 拥有这个任务的插件
     * @param task 要执行的任务
     * @param delay Delay in server ticks before executing task
     * @return 任务id（如果为-1则表示调度失败）
     * @deprecated 这个名称具有误导性，因为它没有调度“同步”任务，而是“异步”任务
     */
    @Deprecated
    public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task, long delay);

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules a once off task to occur as soon as possible. This task will
     * be executed by a thread managed by the scheduler.
     *
     * @param plugin 拥有这个任务的插件
     * @param task 要执行的任务
     * @return 任务id（如果为-1则表示调度失败）
     * @deprecated 这个名称具有误导性，因为它没有调度“同步”任务，而是“异步”任务
     */
    @Deprecated
    public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task);

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules a repeating task. This task will be executed by a thread
     * managed by the scheduler.
     *
     * @param plugin Plugin that owns the task
     * @param task Task to be executed
     * @param delay Delay in server ticks before executing first repeat
     * @param period Period in server ticks of the task
     * @return 任务id（如果为-1则表示调度失败）
     * @deprecated This name is misleading, as it does not schedule "a sync"
     *     task, but rather, "an async" task
     */
    @Deprecated
    public int scheduleAsyncRepeatingTask(Plugin plugin, Runnable task, long delay, long period);

    /**
     * Calls a method on the main thread and returns a Future object. This
     * task will be executed by the main server thread.
     * <ul>
     * <li>Note: The Future.get() methods must NOT be called from the main
     *     thread.
     * <li>Note2: There is at least an average of 10ms latency until the
     *     isDone() method returns true.
     * </ul>
     * @param <T> The callable's return type
     * @param plugin 拥有这个任务的插件
     * @param task 要执行的任务
     * @return Future Future object related to the task
     */
    public <T> Future<T> callSyncMethod(Plugin plugin, Callable<T> task);

    /**
     * 从调度器中移除一个任务。
     * <p>
     * 原文：Removes task from scheduler.
     *
     * @param taskId 要移除的任务的id
     */
    public void cancelTask(int taskId);

    /**
     * 移除与来自调度器中特定相关的插件的所有任务。
     * <p>
     * 原文：Removes all tasks associated with a particular plugin from the
     * scheduler.
     *
     * @param plugin 要移除的所有者的任务
     */
    public void cancelTasks(Plugin plugin);

    /**
     * 从调度器中移除所有任务。
     * <p>
     * 原文：Removes all tasks from the scheduler.
     */
    public void cancelAllTasks();

    /**
     * Check if the task currently running.
     * <p>
     * A repeating task might not be running currently, but will be running in
     * the future. A task that has finished, and does not repeat, will not be
     * running ever again.
     * <p>
     * Explicitly, a task is running if there exists a thread for it, and that
     * thread is alive.
     *
     * @param taskId 要检测的任务
     * <p>
     * @return 任务当前是否在运行
     */
    public boolean isCurrentlyRunning(int taskId);

    /**
     * Check if the task queued to be run later.
     * <p>
     * If a repeating task is currently running, it might not be queued now
     * but could be in the future. A task that is not queued, and not running,
     * will not be queued again.
     *
     * @param taskId The task to check.
     * <p>
     * @return If the task is queued to be run.
     */
    public boolean isQueued(int taskId);

    /**
     * Returns a list of all active workers.
     * <p>
     * This list contains asynch tasks that are being executed by separate
     * threads.
     *
     * @return Active workers
     */
    public List<BukkitWorker> getActiveWorkers();

    /**
     * Returns a list of all pending tasks. The ordering of the tasks is not
     * related to their order of execution.
     *
     * @return Active workers
     */
    public List<BukkitTask> getPendingTasks();

    /**
     * Returns a task that will run on the next server tick.
     *
     * @param plugin the reference to the plugin scheduling task
     * @param task 要运行的任务
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * @throws IllegalArgumentException 如果任务为null
     */
    public BukkitTask runTask(Plugin plugin, Runnable task) throws IllegalArgumentException;

    /**
     * @deprecated 建议使用{@link BukkitRunnable#runTask(Plugin)}
     *
     * @param plugin the reference to the plugin scheduling task
     * @param task the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * @throws IllegalArgumentException 如果任务为null
     */
    @Deprecated
    public BukkitTask runTask(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException;

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Returns a task that will run asynchronously.
     *
     * @param plugin the reference to the plugin scheduling task
     * @param task the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * @throws IllegalArgumentException 如果任务为null
     */
    public BukkitTask runTaskAsynchronously(Plugin plugin, Runnable task) throws IllegalArgumentException;

    /**
     * @deprecated 建议使用{@link BukkitRunnable#runTaskAsynchronously(Plugin)}
     * @param plugin the reference to the plugin scheduling task
     * @param task the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * @throws IllegalArgumentException 如果任务为null
     */
    @Deprecated
    public BukkitTask runTaskAsynchronously(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException;

    /**
     * Returns a task that will run after the specified number of server
     * ticks.
     *
     * @param plugin the reference to the plugin scheduling task
     * @param task the task to be run
     * @param delay the ticks to wait before running the task
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * @throws IllegalArgumentException 如果任务为null
     */
    public BukkitTask runTaskLater(Plugin plugin, Runnable task, long delay) throws IllegalArgumentException;

    /**
     * @deprecated Use {@link BukkitRunnable#runTaskLater(Plugin, long)}
     * @param plugin the reference to the plugin scheduling task
     * @param task the task to be run
     * @param delay the ticks to wait before running the task
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * @throws IllegalArgumentException 如果任务为null
     */
    @Deprecated
    public BukkitTask runTaskLater(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException;

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Returns a task that will run asynchronously after the specified number
     * of server ticks.
     *
     * @param plugin the reference to the plugin scheduling task
     * @param task the task to be run
     * @param delay the ticks to wait before running the task
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * @throws IllegalArgumentException 如果任务为null
     */
    public BukkitTask runTaskLaterAsynchronously(Plugin plugin, Runnable task, long delay) throws IllegalArgumentException;

    /**
     * @deprecated 建议使用{@link BukkitRunnable#runTaskLaterAsynchronously(Plugin, long)}
     * @param plugin the reference to the plugin scheduling task
     * @param task the task to be run
     * @param delay the ticks to wait before running the task
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * @throws IllegalArgumentException 如果任务为null
     */
    @Deprecated
    public BukkitTask runTaskLaterAsynchronously(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException;

    /**
     * Returns a task that will repeatedly run until cancelled, starting after
     * the specified number of server ticks.
     *
     * @param plugin the reference to the plugin scheduling task
     * @param task the task to be run
     * @param delay the ticks to wait before running the task
     * @param period the ticks to wait between runs
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * @throws IllegalArgumentException 如果任务为null
     */
    public BukkitTask runTaskTimer(Plugin plugin, Runnable task, long delay, long period) throws IllegalArgumentException;

    /**
     * @deprecated Use {@link BukkitRunnable#runTaskTimer(Plugin, long, long)}
     * @param plugin the reference to the plugin scheduling task
     * @param task the task to be run
     * @param delay the ticks to wait before running the task
     * @param period the ticks to wait between runs
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * @throws IllegalArgumentException 如果任务为null
     */
    @Deprecated
    public BukkitTask runTaskTimer(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException;

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Returns a task that will repeatedly run asynchronously until cancelled,
     * starting after the specified number of server ticks.
     *
     * @param plugin the reference to the plugin scheduling task
     * @param task the task to be run
     * @param delay the ticks to wait before running the task for the first
     *     time
     * @param period the ticks to wait between runs
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * @throws IllegalArgumentException 如果任务为null
     */
    public BukkitTask runTaskTimerAsynchronously(Plugin plugin, Runnable task, long delay, long period) throws IllegalArgumentException;

    /**
     * @deprecated 建议使用 {@link BukkitRunnable#runTaskTimerAsynchronously(Plugin, long, long)}
     * @param plugin the reference to the plugin scheduling task
     * @param task the task to be run
     * @param delay the ticks to wait before running the task for the first
     *     time
     * @param period the ticks to wait between runs
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * @throws IllegalArgumentException 如果任务为null
     */
    @Deprecated
    public BukkitTask runTaskTimerAsynchronously(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException;
}