package com.huobi.kline.min.dao.btc;

import com.huobi.kline.min.domain.btc.XrpBtcMin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author anonymity
 * @create 2018-04-27 16:30
 **/
@Repository
public interface XrpBtcMinDao extends CrudRepository<XrpBtcMin, Long> {

    @Query(value = "select z from XrpBtcMin as z where z.id=(select max(z.id) from XrpBtcMin as z)")
    XrpBtcMin findLastTimeData();

}
