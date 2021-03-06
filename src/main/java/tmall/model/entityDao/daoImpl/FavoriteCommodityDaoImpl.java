package tmall.model.entityDao.daoImpl;

import tmall.XMLRepository.ProxyXmlContext;
import tmall.XMLRepository.XMLContext;
import tmall.model.entity.Commodity;
import tmall.model.entity.FollowCommodity;
import tmall.model.entity.FollowShop;
import tmall.model.entity.ShoppingCart;
import tmall.model.entityDao.daoInterface.FavoriteCommodityDao;
import tmall.model.logicalEntity.FollowCommodityLogic;
import tmall.model.logicalEntity.FollowShopLogic;
import tmall.model.logicalEntity.ShoppingCartLogic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FavoriteCommodityDaoImpl implements FavoriteCommodityDao {

    private final XMLContext<FollowCommodity> followCommodityXMLContext = new ProxyXmlContext<>(FollowCommodity.class);

    private final XMLContext<Commodity> commodityXMLContext = new ProxyXmlContext<>(Commodity.class);

    private FollowCommodity followCommodity;

    /**
     * 关注商品
     *
     * @param buyerId     买家id
     * @param commodityId 商品id
     * @return a follow commodity
     */
    @Override
    public FollowCommodity create(String buyerId, String commodityId) {
        // get the current date and time
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        followCommodity = new FollowCommodity(buyerId, commodityId, dateTime.format(formatter));
        return followCommodity;
    }

    /**
     * 存储到数据库中
     */
    @Override
    public void addToDb() {
        followCommodityXMLContext.add(followCommodity);
    }

    /**
     * 获取买家收藏夹的详细信息
     *
     * @param buyerId 买家id
     * @return list commodity details
     */
    @Override
    public List<FollowCommodityLogic> getByBuyerId(String buyerId) {
        List<FollowCommodityLogic> followCommodityLogicList = new ArrayList<>();

        List<FollowCommodity> init = followCommodityXMLContext.init();
        for (FollowCommodity fc : init) {
            if(fc.getBuyerId().equals(buyerId)){
                Commodity c = commodityXMLContext.findById(fc.getCommodityId());
                FollowCommodityLogic followCommodityLogic = new FollowCommodityLogic(c.getCommodityId(), c.getCname(), c.getPrice(), c.getCategory(), c.getDescription(), fc.getFollowDate());
                followCommodityLogicList.add(followCommodityLogic);
            }
        }
        return followCommodityLogicList;
    }
}
