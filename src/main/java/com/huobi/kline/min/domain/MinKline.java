package com.huobi.kline.min.domain;

import lombok.Data;

import java.util.List;

/**
 * @author anonymity
 * @create 2018-04-27 16:45
 **/
@Data
public class MinKline<T> {
    private String status;
    private String ch;
    private Long tx;
    private List<T> data;
}
