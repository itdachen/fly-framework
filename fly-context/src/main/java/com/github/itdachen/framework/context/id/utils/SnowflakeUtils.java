package com.github.itdachen.framework.context.id.utils;

/**
 * Description: 雪花算法生成分布式全局唯一id(有序)
 * Created by 王大宸 on 2023/01/05 9:31
 * Created with IntelliJ IDEA.
 */
public class SnowflakeUtils {


    private long workerId;
    private long datacenterId;
    private long sequence;

    private long twepoch = 1288834974657L;

    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private long sequenceBits = 12L;

    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;

    public SnowflakeUtils(long workerId, long datacenterId, long sequence) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
//        System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
//                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public long getTwepoch() {
        return twepoch;
    }

    public void setTwepoch(long twepoch) {
        this.twepoch = twepoch;
    }

    public long getWorkerIdBits() {
        return workerIdBits;
    }

    public void setWorkerIdBits(long workerIdBits) {
        this.workerIdBits = workerIdBits;
    }

    public long getDatacenterIdBits() {
        return datacenterIdBits;
    }

    public void setDatacenterIdBits(long datacenterIdBits) {
        this.datacenterIdBits = datacenterIdBits;
    }

    public long getMaxWorkerId() {
        return maxWorkerId;
    }

    public void setMaxWorkerId(long maxWorkerId) {
        this.maxWorkerId = maxWorkerId;
    }

    public long getMaxDatacenterId() {
        return maxDatacenterId;
    }

    public void setMaxDatacenterId(long maxDatacenterId) {
        this.maxDatacenterId = maxDatacenterId;
    }

    public long getSequenceBits() {
        return sequenceBits;
    }

    public void setSequenceBits(long sequenceBits) {
        this.sequenceBits = sequenceBits;
    }

    public long getWorkerIdShift() {
        return workerIdShift;
    }

    public void setWorkerIdShift(long workerIdShift) {
        this.workerIdShift = workerIdShift;
    }

    public long getDatacenterIdShift() {
        return datacenterIdShift;
    }

    public void setDatacenterIdShift(long datacenterIdShift) {
        this.datacenterIdShift = datacenterIdShift;
    }

    public long getTimestampLeftShift() {
        return timestampLeftShift;
    }

    public void setTimestampLeftShift(long timestampLeftShift) {
        this.timestampLeftShift = timestampLeftShift;
    }

    public long getSequenceMask() {
        return sequenceMask;
    }

    public void setSequenceMask(long sequenceMask) {
        this.sequenceMask = sequenceMask;
    }

    public long getLastTimestamp() {
        return lastTimestamp;
    }

    public void setLastTimestamp(long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }


    //    public static void main(String[] args) {
//        IdWorkerUtil idWorkerUtil = new IdWorkerUtil(1, 1, 0L);
//        System.out.println(idWorkerUtil.nextId());
//    }

}
