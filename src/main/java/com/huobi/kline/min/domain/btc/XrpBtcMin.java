package com.huobi.kline.min.domain.btc;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author anonymity
 * @create 2018-04-27 16:29
 **/
@Data
@Entity
@Table(name = "xrpbtc_min")
public class XrpBtcMin {

    @Id
    private Long id;

    @Column(precision = 40, scale = 20)
    private BigDecimal amount;
    private Long count;
    @Column(precision = 40, scale = 20)
    private BigDecimal open;
    @Column(precision = 40, scale = 20)
    private BigDecimal close;
    @Column(precision = 40, scale = 20)
    private BigDecimal low;
    @Column(precision = 40, scale = 20)
    private BigDecimal high;
    @Column(precision = 40, scale = 20)
    private BigDecimal vol;
}
