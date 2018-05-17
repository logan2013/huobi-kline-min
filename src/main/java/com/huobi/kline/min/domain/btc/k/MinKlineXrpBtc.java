package com.huobi.kline.min.domain.btc.k;

import com.huobi.kline.min.domain.btc.XrpBtcMin;
import com.huobi.kline.min.domain.eth.EosEthMin;
import lombok.Data;

import java.util.List;

/**
 * @author anonymity
 * @create 2018-04-27 16:45
 **/
@Data
public class MinKlineXrpBtc {
    private String status;
    private String ch;
    private Long tx;
    private List<XrpBtcMin> data;
}
