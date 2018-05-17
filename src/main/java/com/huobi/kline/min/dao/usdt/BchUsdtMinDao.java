package com.huobi.kline.min.dao.usdt;

import com.huobi.kline.min.domain.usdt.BchUsdtMin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author anonymity
 * @create 2018-04-27 16:30
 **/
@Repository
public interface BchUsdtMinDao extends CrudRepository<BchUsdtMin, Long> {

    @Query(value = "select z from BchUsdtMin as z where z.id=(select max(z.id) from BchUsdtMin as z)")
    BchUsdtMin findLastTimeData();

}
