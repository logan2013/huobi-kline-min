package com.huobi.kline.min.service;

import com.huobi.kline.min.dao.btc.*;
import com.huobi.kline.min.dao.eth.EosEthMinDao;
import com.huobi.kline.min.dao.eth.OmgEthMinDao;
import com.huobi.kline.min.dao.usdt.*;
import com.huobi.kline.min.domain.btc.*;
import com.huobi.kline.min.domain.btc.k.*;
import com.huobi.kline.min.domain.eth.EosEthMin;
import com.huobi.kline.min.domain.eth.OmgEthMin;
import com.huobi.kline.min.domain.eth.k.MinKlineEosEth;
import com.huobi.kline.min.domain.eth.k.MinKlineOmgEth;
import com.huobi.kline.min.domain.usdt.*;
import com.huobi.kline.min.domain.usdt.k.*;
import com.huobi.kline.util.HttpUtils;
import com.huobi.kline.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.huobi.kline.common.HuoBiConst.*;

/**
 * @author anonymity
 * @create 2018-04-27 16:31
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class MinKlineService {
    private static final Logger LOG = LoggerFactory.getLogger(MinKlineService.class);

    @Resource
    private ZecUsdtMinDao zecUsdtMinDao;
    @Resource
    private XrpUsdtMinDao xrpUsdtMinDao;
    @Resource
    private OmgUsdtMinDao omgUsdtMinDao;
    @Resource
    private LtcUsdtMinDao ltcUsdtMinDao;
    @Resource
    private EtcUsdtMinDao etcUsdtMinDao;
    @Resource
    private DashUsdtMinDao dashUsdtMinDao;
    @Resource
    private EosUsdtMinDao eosUsdtMinDao;
    @Resource
    private EthUsdtMinDao ethUsdtMinDao;
    @Resource
    private BtcUsdtMinDao btcUsdtMinDao;
    @Resource
    private BchUsdtMinDao bchUsdtMinDao;

    @Resource
    private OmgEthMinDao omgEthMinDao;
    @Resource
    private EosEthMinDao eosEthMinDao;

    @Resource
    private ZecBtcMinDao zecBtcMinDao;
    @Resource
    private XrpBtcMinDao xrpBtcMinDao;
    @Resource
    private OmgBtcMinDao omgBtcMinDao;
    @Resource
    private LtcBtcMinDao ltcBtcMinDao;
    @Resource
    private EtcBtcMinDao etcBtcMinDao;
    @Resource
    private EthBtcMinDao ethBtcMinDao;
    @Resource
    private EosBtcMinDao eosBtcMinDao;
    @Resource
    private DashBtcMinDao dashBtcMinDao;
    @Resource
    private BchBtcMinDao bchBtcMinDao;


    // ####################### BTC ############################## //

    public void scheduledZec2Btc() {
        try {
            List<ZecBtcMin> list = zec2btc();
            if (list != null && list.size() != 0) {
                ZecBtcMin data = zecBtcMinDao.findLastTimeData();
                if (data != null) {
                    List<ZecBtcMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> zecBtcMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the zec to btc 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<ZecBtcMin> zec2btc() {
        try {
            String json = getJson(ZECBTC);
            if (json != null) {
                MinKlineZecBtc minKline = JsonUtil.jsonToEntity(json, MinKlineZecBtc.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query zec to btc 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledXrp2Btc() {
        try {
            List<XrpBtcMin> list = xrp2btc();
            if (list != null) {
                XrpBtcMin data = xrpBtcMinDao.findLastTimeData();
                if (data != null) {
                    List<XrpBtcMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> xrpBtcMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the xrp to btc 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<XrpBtcMin> xrp2btc() {
        try {
            String json = getJson(XRPBTC);
            if (json != null) {
                MinKlineXrpBtc minKline = JsonUtil.jsonToEntity(json, MinKlineXrpBtc.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query xrp to btc 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledOmg2Btc() {
        try {
            List<OmgBtcMin> list = omg2btc();
            if (list != null) {
                OmgBtcMin data = omgBtcMinDao.findLastTimeData();
                if (data != null) {
                    List<OmgBtcMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> omgBtcMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the omg to btc 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<OmgBtcMin> omg2btc() {
        try {
            String json = getJson(OMGBTC);
            if (json != null) {
                MinKlineOmgBtc minKline = JsonUtil.jsonToEntity(json, MinKlineOmgBtc.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query omg to btc 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledLtc2Btc() {
        try {
            List<LtcBtcMin> list = ltc2btc();
            if (list != null) {
                LtcBtcMin data = ltcBtcMinDao.findLastTimeData();
                if (data != null) {
                    List<LtcBtcMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> ltcBtcMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the ltc to btc 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<LtcBtcMin> ltc2btc() {
        try {
            String json = getJson(LTCBTC);
            if (json != null) {
                MinKlineLtcBtc minKline = JsonUtil.jsonToEntity(json, MinKlineLtcBtc.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query ltc to btc 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledEtc2Btc() {
        try {
            List<EtcBtcMin> list = etc2btc();
            if (list != null) {
                EtcBtcMin data = etcBtcMinDao.findLastTimeData();
                if (data != null) {
                    List<EtcBtcMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> etcBtcMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the etc to btc 1min kline time task failed: {}", e.getMessage(), e);
        }

    }

    private List<EtcBtcMin> etc2btc() {
        try {
            String json = getJson(ETCBTC);
            if (json != null) {
                MinKlineEtcBtc minKline = JsonUtil.jsonToEntity(json, MinKlineEtcBtc.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query etc to btc 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledEth2Btc() {
        try {
            List<EthBtcMin> list = eth2btc();
            if (list != null) {
                EthBtcMin data = ethBtcMinDao.findLastTimeData();
                if (data != null) {
                    List<EthBtcMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> ethBtcMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the etc to btc 1min kline time task failed: {}", e.getMessage(), e);
        }

    }

    private List<EthBtcMin> eth2btc() {
        try {
            String json = getJson(ETHBTC);
            if (json != null) {
                MinKlineEthBtc minKline = JsonUtil.jsonToEntity(json, MinKlineEthBtc.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query etc to btc 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledEos2Btc() {
        try {
            List<EosBtcMin> list = eos2btc();
            if (list != null) {
                EosBtcMin data = eosBtcMinDao.findLastTimeData();
                if (data != null) {
                    List<EosBtcMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> eosBtcMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the eos to btc 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<EosBtcMin> eos2btc() {
        try {
            String json = getJson(EOSBTC);
            if (json != null) {
                MinKlineEosBtc minKline = JsonUtil.jsonToEntity(json, MinKlineEosBtc.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query eos to btc 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledDash2Btc() {
        try {
            List<DashBtcMin> list = dash2btc();
            if (list != null) {
                DashBtcMin data = dashBtcMinDao.findLastTimeData();
                if (data != null) {
                    List<DashBtcMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> dashBtcMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the dash to btc 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<DashBtcMin> dash2btc() {
        try {
            String json = getJson(DASHBTC);
            if (json != null) {
                MinKlineDashBtc minKline = JsonUtil.jsonToEntity(json, MinKlineDashBtc.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query dash to btc 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledBch2Btc() {
        try {
            List<BchBtcMin> list = bch2btc();
            if (list != null) {
                BchBtcMin data = bchBtcMinDao.findLastTimeData();
                if (data != null) {
                    List<BchBtcMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> bchBtcMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the bch to btc 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<BchBtcMin> bch2btc() {
        try {
            String json = getJson(BCHBTC);
            if (json != null) {
                MinKlineBchBtc minKline = JsonUtil.jsonToEntity(json, MinKlineBchBtc.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query bch to btc 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }

    // ############################# ETH ######################## //

    public void scheduledEos2Eth() {
        try {
            List<EosEthMin> list = eos2eth();
            if (list != null) {
                EosEthMin data = eosEthMinDao.findLastTimeData();
                if (data != null) {
                    List<EosEthMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> eosEthMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the eos to eth 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<EosEthMin> eos2eth() {
        try {
            String json = getJson(EOSETH);
            if (json != null) {
                MinKlineEosEth minKline = JsonUtil.jsonToEntity(json, MinKlineEosEth.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query eos to eth 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledOmg2Eth() {
        try {
            List<OmgEthMin> list = omg2eth();
            if (list != null) {
                OmgEthMin data = omgEthMinDao.findLastTimeData();
                if (data != null) {
                    List<OmgEthMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> omgEthMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the omg to eth 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<OmgEthMin> omg2eth() {
        try {
            String json = getJson(OMGETH);
            if (json != null) {
                MinKlineOmgEth minKline = JsonUtil.jsonToEntity(json, MinKlineOmgEth.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query omg to eth 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }
    // ############################ USDT ######################### //

    public void scheduledBch2Usdt() {
        try {
            List<BchUsdtMin> list = bch2usdt();
            if (list != null) {
                BchUsdtMin data = bchUsdtMinDao.findLastTimeData();
                if (data != null) {
                    List<BchUsdtMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> bchUsdtMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the bch to usdt 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<BchUsdtMin> bch2usdt() {
        try {
            String json = getJson(BCHUSDT);
            if (json != null) {
                MinKlineBchUsdt minKline = JsonUtil.jsonToEntity(json, MinKlineBchUsdt.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query bch to usdt 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledBtc2Usdt() {
        try {
            List<BtcUsdtMin> list = btc2usdt();
            if (list != null) {
                BtcUsdtMin data = btcUsdtMinDao.findLastTimeData();
                if (data != null) {
                    List<BtcUsdtMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> btcUsdtMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the btc to usdt 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<BtcUsdtMin> btc2usdt() {
        try {
            String json = getJson(BTCUSDT);
            if (json != null) {
                MinKlineBtcUsdt minKline = JsonUtil.jsonToEntity(json, MinKlineBtcUsdt.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query btc to usdt 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledEth2Usdt() {
        try {
            List<EthUsdtMin> list = eth2usdt();
            if (list != null) {
                EthUsdtMin data = ethUsdtMinDao.findLastTimeData();
                if (data != null) {
                    List<EthUsdtMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> ethUsdtMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }

                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the eth to usdt 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<EthUsdtMin> eth2usdt() {
        try {
            String json = getJson(ETHUSDT);
            if (json != null) {
                MinKlineEthUsdt minKline = JsonUtil.jsonToEntity(json, MinKlineEthUsdt.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query eth to usdt 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledEos2Usdt() {
        try {
            List<EosUsdtMin> list = eos2usdt();
            if (list != null) {
                EosUsdtMin data = eosUsdtMinDao.findLastTimeData();
                if (data != null) {
                    List<EosUsdtMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> eosUsdtMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the eos to usdt 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<EosUsdtMin> eos2usdt() {
        try {
            String json = getJson(EOSUSDT);
            if (json != null) {
                MinKlineEosUsdt minKline = JsonUtil.jsonToEntity(json, MinKlineEosUsdt.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query eos to usdt 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledDash2Usdt() {
        try {
            List<DashUsdtMin> list = dash2usdt();
            if (list != null) {
                DashUsdtMin data = dashUsdtMinDao.findLastTimeData();
                if (data != null) {
                    List<DashUsdtMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> dashUsdtMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }

                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the dash to usdt 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<DashUsdtMin> dash2usdt() {
        try {
            String json = getJson(DASHUSDT);
            if (json != null) {
                MinKlineDashUsdt minKline = JsonUtil.jsonToEntity(json, MinKlineDashUsdt.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query dash to usdt 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledEtc2Usdt() {
        try {
            List<EtcUsdtMin> list = etc2usdt();
            if (list != null) {
                EtcUsdtMin data = etcUsdtMinDao.findLastTimeData();
                if (data != null) {
                    List<EtcUsdtMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> etcUsdtMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }

                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the etc to usdt 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<EtcUsdtMin> etc2usdt() {
        try {
            String json = getJson(ETCUSDT);
            if (json != null) {
                MinKlineEtcUsdt minKline = JsonUtil.jsonToEntity(json, MinKlineEtcUsdt.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query etc to usdt 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledLtc2Usdt() {
        try {
            List<LtcUsdtMin> list = ltc2usdt();
            if (list != null) {
                LtcUsdtMin data = ltcUsdtMinDao.findLastTimeData();
                if (data != null) {
                    List<LtcUsdtMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> ltcUsdtMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }

                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the ltc to usdt 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<LtcUsdtMin> ltc2usdt() {
        try {
            String json = getJson(LTCUSDT);
            if (json != null) {
                MinKlineLtcUsdt minKline = JsonUtil.jsonToEntity(json, MinKlineLtcUsdt.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query ltc to usdt 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledOmg2Usdt() {
        try {
            List<OmgUsdtMin> list = omg2usdt();
            if (list != null) {
                OmgUsdtMin data = omgUsdtMinDao.findLastTimeData();
                if (data != null) {
                    List<OmgUsdtMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> omgUsdtMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }

                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the omg to usdt 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<OmgUsdtMin> omg2usdt() {
        try {
            String json = getJson(OMGUSDT);
            if (json != null) {
                MinKlineOmgUsdt minKline = JsonUtil.jsonToEntity(json, MinKlineOmgUsdt.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query omg to usdt 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledZec2Usdt() {
        try {
            List<ZecUsdtMin> list = zec2usdt();
            if (list != null) {
                ZecUsdtMin data = zecUsdtMinDao.findLastTimeData();
                if (data != null) {
                    // 获取最终要保存到数据库的数据list
                    List<ZecUsdtMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> zecUsdtMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the zec to usdt 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<ZecUsdtMin> zec2usdt() {
        try {
            String json = getJson(ZECUSDT);
            if (json != null) {
                MinKlineZecUsdt minKline = JsonUtil.jsonToEntity(json, MinKlineZecUsdt.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query zec to usdt 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }


    public void scheduledXrp2Usdt() {
        try {
            List<XrpUsdtMin> list = xrp2usdt();
            if (list != null) {
                XrpUsdtMin data = xrpUsdtMinDao.findLastTimeData();
                if (data != null) {
                    List<XrpUsdtMin> list2 = list.stream().filter(l -> l.getId() > data.getId()).collect(Collectors.toList());
                    if (list2.size() != 0) {
                        list2.stream().forEach(l -> xrpUsdtMinDao.save(l));
                    } else {
                        throw new Exception("数据异常");
                    }
                } else {
                    throw new Exception("数据库没有历史数据，请通过websocket获取历史数据");
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.error("Get the xrp to usdt 1min kline time task failed: {}", e.getMessage(), e);
        }
    }

    private List<XrpUsdtMin> xrp2usdt() {
        try {
            String json = getJson(XRPUSDT);
            if (json != null) {
                MinKlineXrpUsdt minKline = JsonUtil.jsonToEntity(json, MinKlineXrpUsdt.class);
                if (OK.equals(minKline.getStatus())) {
                    return minKline.getData();
                }
            }
        } catch (Exception e) {
            LOG.error("query xrp to usdt 1min kline failed: {}", e.getMessage(), e);
        }
        return null;
    }

    private String getJson(String symbol) {
        try {
//            String url = HUOBI_BR_KLINE_URL + "?symbol=" + symbol + "&period=1min&size=1000";
//            String json = HttpUtils.sendGet(url, false);
//            if (json != null && !json.isEmpty()) {
//                return json;
//            }
            // 如果使用br站获取失败，使用pro站加入代理在访问一次
            String url2 = HUOBI_KLINE_URL + "?symbol=" + symbol + "&period=1min&size=1000";
            String json = HttpUtils.sendGet(url2, true);
            if (json != null && !json.isEmpty()) {
                return json;
            }
        } catch (Exception e) {
            LOG.error("getJson failed: {}", e.getMessage(), e);
        }
        return null;
    }
}
