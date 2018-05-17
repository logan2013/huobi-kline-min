package com.huobi.kline.min.domain.eth.k;

import com.huobi.kline.min.domain.eth.EosEthMin;
import com.huobi.kline.min.domain.eth.OmgEthMin;
import lombok.Data;

import java.util.List;

/**
 * @author anonymity
 * @create 2018-04-27 16:45
 **/
@Data
public class MinKlineEosEth {
    private String status;
    private String ch;
    private Long tx;
    private List<EosEthMin> data;
}
