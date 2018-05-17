package com.huobi.kline.min.dao.usdt;

import com.huobi.kline.min.domain.usdt.OmgUsdtMin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author anonymity
 * @create 2018-04-27 16:30
 **/
@Repository
public interface OmgUsdtMinDao extends CrudRepository<OmgUsdtMin, Long> {

    @Query(value = "select z from OmgUsdtMin as z where z.id=(select max(z.id) from OmgUsdtMin as z)")
    OmgUsdtMin findLastTimeData();

}
