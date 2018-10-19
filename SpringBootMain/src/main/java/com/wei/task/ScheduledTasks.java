package com.wei.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**开启任务调度
 * Created by we.peng on 2018/10/19.
 */
@Component
@EnableScheduling
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 每10秒执行一次
     *
     * @author zhangyd
     */
    @Scheduled(cron = "0/10 * * * * ? ")
    public void doJobByCron() {
        log.info(new Date() + "-----------------doJobByCron");
    }
    /**
     * 固定每5秒执行一次
     *
     * @author zhangyd
     */
    @Scheduled(fixedRate = 5 * 1000)
    public void doJobByFixedRate() {
        log.info(new Date() + "-----------------doJobByFixedRate");
    }
    /**
     * 上次任务结束后一秒后再次执行
     *
     * @author zhangyd
     */
    @Scheduled(fixedDelay = 1 * 1000)
    public void doJobByFixedDelay() {
        log.info(new Date() + "******************doJobByFixedDelay");
    }
    /**
     * 第一次延迟1秒后执行，之后按fixedRate的规则每2秒执行一次
     *
     * @author zhangyd
     */
    @Scheduled(initialDelay = 1000, fixedRate = 2000)
    public void doInitialDelay() {
        log.info(new Date() + "+++++++++++++++++++doInitialDelay");
    }
}
