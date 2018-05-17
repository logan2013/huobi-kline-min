package com.huobi.kline.min.dao.btc;

import com.huobi.kline.min.domain.btc.LtcBtcMin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author anonymity
 * @create 2018-04-27 16:30
 **/
@Repository
public interface LtcBtcMinDao extends CrudRepository<LtcBtcMin, Long> {

    @Query(value = "select z from LtcBtcMin as z where z.id=(select max(z.id) from LtcBtcMin as z)")
    LtcBtcMin findLastTimeData();

}
