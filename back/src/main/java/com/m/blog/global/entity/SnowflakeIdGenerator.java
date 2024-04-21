package com.m.blog.global.entity;

import com.m.blog.global.exception.CustomIllegalArgumentException;
import com.m.blog.global.exception.SnowflakeException;

public class SnowflakeIdGenerator {
    // 데이터센터 ID와 서버 ID는 초기에 설정되어야 합니다.
    private static long dataCenterId;
    private static long serverId;

    private static long sequence = 0L;
    private static final long dataCenterIdBits = 5L;
    private static final long serverIdBits = 5L;
    private static final long sequenceBits = 12L;

    private static final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    private static final long maxServerId = -1L ^ (-1L << serverIdBits);

    private static final long timestampLeftShift = sequenceBits + serverIdBits + dataCenterIdBits;
    private static final long dataCenterIdShift = sequenceBits + serverIdBits;
    private static final long serverIdShift = sequenceBits;

    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);
    private static long lastTimestamp = -1L;

    public static synchronized void setDataCenterId(long dataCenterId) {
        if (dataCenterId < 0 || dataCenterId > maxDataCenterId) {
            throw new CustomIllegalArgumentException("Data Center ID must be between 0 and " + maxDataCenterId);
        }
        SnowflakeIdGenerator.dataCenterId = dataCenterId;
    }

    public static synchronized void setServerId(long serverId) {
        if (serverId < 0 || serverId > maxServerId) {
            throw new CustomIllegalArgumentException("Server ID must be between 0 and " + maxServerId);
        }
        SnowflakeIdGenerator.serverId = serverId;
    }

    public static synchronized String generateId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new SnowflakeException("Clock moved backwards. Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 같은 ms 동안에 하나 이상의 ID를 만들어 낸 경우에만 대기
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        long generatedId = ((timestamp - 0L) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (serverId << serverIdShift) |
                sequence;
        return String.valueOf(generatedId);
    }

    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        SnowflakeIdGenerator.setDataCenterId(1);
        SnowflakeIdGenerator.setServerId(1);

        // ID 생성 예시
        for (int i = 0; i < 12; i++) {
            String id = SnowflakeIdGenerator.generateId();
            System.out.println((i+1) + ". Generated ID: " + id);
        }
    }
}
