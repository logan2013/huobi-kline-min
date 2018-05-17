package com.huobi.kline.min.dao.usdt;

import com.huobi.kline.min.domain.usdt.BtcUsdtMin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author anonymity
 * @create 2018-04-27 16:30
 **/
@Repository
public interface BtcUsdtMinDao extends CrudRepository<BtcUsdtMin, Long> {

    @Query(value = "select z from BtcUsdtMin as z where z.id=(select max(z.id) from BtcUsdtMin as z)")
    BtcUsdtMin findLastTimeData();

}
