package com.github.itdachen.framework.context.id.sharding;

import java.util.Calendar;
import java.util.Properties;

/**
 * Description: 雪花算法
 * Created by 王大宸 on 2023-06-19 23:26
 * Created with IntelliJ IDEA.
 */
public final class SnowflakeShardingKeyGeneratorUtils {
    public static final long EPOCH;
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_BITS = 10L;
    private static final long SEQUENCE_MASK = 4095L;
    private static final long WORKER_ID_LEFT_SHIFT_BITS = 12L;
    private static final long TIMESTAMP_LEFT_SHIFT_BITS = 22L;
    private static final long WORKER_ID_MAX_VALUE = 1024L;
    private static final long WORKER_ID = 0L;
    private static final int DEFAULT_VIBRATION_VALUE = 1;
    private static final int MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS = 10;
    private static TimeService timeService = new TimeService();
    private Properties properties = new Properties();
    private int sequenceOffset = -1;
    private long sequence;
    private long lastMilliseconds;

    public SnowflakeShardingKeyGeneratorUtils() {
    }

    public String getType() {
        return "SNOWFLAKE";
    }

    public synchronized Comparable<?> generateKey() {
        long currentMilliseconds = timeService.getCurrentMillis();
        if (this.waitTolerateTimeDifferenceIfNeed(currentMilliseconds)) {
            currentMilliseconds = timeService.getCurrentMillis();
        }

        if (this.lastMilliseconds == currentMilliseconds) {
            if (0L == (this.sequence = this.sequence + 1L & 4095L)) {
                currentMilliseconds = this.waitUntilNextTime(currentMilliseconds);
            }
        } else {
            this.vibrateSequenceOffset();
            this.sequence = (long) this.sequenceOffset;
        }

        this.lastMilliseconds = currentMilliseconds;
        return currentMilliseconds - EPOCH << 22 | this.getWorkerId() << 12 | this.sequence;
    }

    private boolean waitTolerateTimeDifferenceIfNeed(long currentMilliseconds) {
        try {
            if (this.lastMilliseconds <= currentMilliseconds) {
                return false;
            } else {
                long timeDifferenceMilliseconds = this.lastMilliseconds - currentMilliseconds;
                Preconditions.checkState(timeDifferenceMilliseconds < (long) this.getMaxTolerateTimeDifferenceMilliseconds(), "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", new Object[]{this.lastMilliseconds, currentMilliseconds});
                Thread.sleep(timeDifferenceMilliseconds);
                return true;
            }
        } catch (Throwable var5) {
          //  throw false;
            return false;
        }
    }

    private long getWorkerId() {
        long result = Long.valueOf(this.properties.getProperty("worker.id", String.valueOf(0L)));
        Preconditions.checkArgument(result >= 0L && result < 1024L);
        return result;
    }

    private int getMaxVibrationOffset() {
        int result = Integer.parseInt(this.properties.getProperty("max.vibration.offset", String.valueOf(1)));
        Preconditions.checkArgument(result >= 0 && (long) result <= 4095L, "Illegal max vibration offset");
        return result;
    }

    private int getMaxTolerateTimeDifferenceMilliseconds() {
        return Integer.valueOf(this.properties.getProperty("max.tolerate.time.difference.milliseconds", String.valueOf(10)));
    }

    private long waitUntilNextTime(long lastTime) {
        long result;
        for (result = timeService.getCurrentMillis(); result <= lastTime; result = timeService.getCurrentMillis()) {
        }

        return result;
    }

    private void vibrateSequenceOffset() {
        this.sequenceOffset = this.sequenceOffset >= this.getMaxVibrationOffset() ? 0 : this.sequenceOffset + 1;
    }

    public static void setTimeService(TimeService timeService) {
        SnowflakeShardingKeyGeneratorUtils.timeService = timeService;
    }

    public Properties getProperties() {
        return this.properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 10, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        EPOCH = calendar.getTimeInMillis();
    }

}
