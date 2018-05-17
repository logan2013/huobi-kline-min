package com.huobi.kline.min.domain.usdt.k;

import com.huobi.kline.min.domain.usdt.XrpUsdtMin;
import com.huobi.kline.min.domain.usdt.ZecUsdtMin;
import lombok.Data;

import java.util.List;

/**
 * @author anonymity
 * @create 2018-04-27 16:45
 **/
@Data
public class MinKlineXrpUsdt {
    private String status;
    private String ch;
    private Long tx;
    private List<XrpUsdtMin> data;
}
