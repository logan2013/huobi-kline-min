package com.huobi.kline.min.domain.usdt.k;

import com.huobi.kline.min.domain.usdt.LtcUsdtMin;
import com.huobi.kline.min.domain.usdt.ZecUsdtMin;
import lombok.Data;

import java.util.List;

/**
 * @author anonymity
 * @create 2018-04-27 16:45
 **/
@Data
public class MinKlineLtcUsdt {
    private String status;
    private String ch;
    private Long tx;
    private List<LtcUsdtMin> data;
}
