package com.huobi.kline.min.domain.btc.k;

import com.huobi.kline.min.domain.btc.OmgBtcMin;
import com.huobi.kline.min.domain.btc.ZecBtcMin;
import lombok.Data;

import java.util.List;

/**
 * @author anonymity
 * @create 2018-04-27 16:45
 **/
@Data
public class MinKlineOmgBtc {
    private String status;
    private String ch;
    private Long tx;
    private List<OmgBtcMin> data;
}
