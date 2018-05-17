package com.huobi.kline.min.dao.eth;

import com.huobi.kline.min.domain.eth.EosEthMin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author anonymity
 * @create 2018-04-27 16:30
 **/
@Repository
public interface EosEthMinDao extends CrudRepository<EosEthMin, Long> {

    @Query(value = "select z from EosEthMin as z where z.id=(select max(z.id) from EosEthMin as z)")
    EosEthMin findLastTimeData();

}
