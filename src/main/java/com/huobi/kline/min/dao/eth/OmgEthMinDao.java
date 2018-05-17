package com.huobi.kline.min.dao.eth;

import com.huobi.kline.min.domain.eth.OmgEthMin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author anonymity
 * @create 2018-04-27 16:30
 **/
@Repository
public interface OmgEthMinDao extends CrudRepository<OmgEthMin, Long> {

    @Query(value = "select z from OmgEthMin as z where z.id=(select max(z.id) from OmgEthMin as z)")
    OmgEthMin findLastTimeData();

}
