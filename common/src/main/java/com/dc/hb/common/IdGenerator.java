package com.dc.hb.common;

public class IdGenerator {
    private static long seqTimestamp;
    private static long seq = 0L;

    public static Long getId(long idType, long machineId) {
        if (idType < 32L && idType >= 0L) {
            if (machineId < 32L && machineId >= 0L) {
                long id = 0L;
                synchronized(IdGenerator.class) {
                    long currentTimestamp = System.currentTimeMillis();
                    if (currentTimestamp == seqTimestamp) {
                        seq = seq + 1L & 4095L;
                        if (seq == 0L) {
                            return getId(idType, machineId);
                        }
                    } else {
                        seqTimestamp = currentTimestamp;
                        seq = 0L;
                    }

                    id = idType << 58 | machineId << 53 | currentTimestamp << 12 | seq;
                }

                return id;
            } else {
                throw new RuntimeException("机器编码值错误,取值0-31");
            }
        } else {
            throw new RuntimeException("序号类型值错误,取值0-31");
        }
    }
}
