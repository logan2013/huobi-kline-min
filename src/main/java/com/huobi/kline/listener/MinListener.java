package com.huobi.kline.listener;

import com.huobi.kline.min.service.MinKlineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * @author anonymity
 * @create 2018-04-27 17:49
 **/
@Configuration
public class MinListener {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MinKlineService minKlineService;

    // 5分钟执行一次
    @Scheduled(fixedRate = 1000 * 300)
    public void getDayKline() {
        LOG.info("==定时任务开始==");
        coin2UsdtTask();
        coin2EthTask();
        coin2BtcTask();
        LOG.info("==定时任务结束==");
    }

    private void coin2BtcTask() {
        minKlineService.scheduledZec2Btc();
        minKlineService.scheduledXrp2Btc();
        minKlineService.scheduledOmg2Btc();
        minKlineService.scheduledLtc2Btc();
        minKlineService.scheduledEtc2Btc();
        minKlineService.scheduledEth2Btc();
        minKlineService.scheduledEos2Btc();
        minKlineService.scheduledDash2Btc();
        minKlineService.scheduledBch2Btc();
    }

    private void coin2EthTask() {
        minKlineService.scheduledOmg2Eth();
        minKlineService.scheduledEos2Eth();
    }

    private void coin2UsdtTask() {
        minKlineService.scheduledZec2Usdt();
        minKlineService.scheduledOmg2Usdt();
        minKlineService.scheduledEtc2Usdt();
        minKlineService.scheduledDash2Usdt();
        // 数据库之前有这些的数据
        minKlineService.scheduledLtc2Usdt();
        minKlineService.scheduledXrp2Usdt();
        minKlineService.scheduledEos2Usdt();
        minKlineService.scheduledEth2Usdt();
        minKlineService.scheduledBtc2Usdt();
        minKlineService.scheduledBch2Usdt();
    }
}
