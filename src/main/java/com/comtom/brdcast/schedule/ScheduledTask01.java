package com.comtom.brdcast.schedule;

import com.comtom.brdcast.schedule.interfaces.ScheduledTaskJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务 01
 */
public class ScheduledTask01 implements ScheduledTaskJob {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask01.class);

    @Override
    public void run() {
        LOGGER.info("ScheduledTask => 01  run  当前线程名称 {} ", Thread.currentThread().getName());
    }
}