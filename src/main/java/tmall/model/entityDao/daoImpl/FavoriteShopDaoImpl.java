package tmall.model.entityDao.daoImpl;

import tmall.XMLRepository.ProxyXmlContext;
import tmall.XMLRepository.XMLContext;
import tmall.model.entity.Commodity;
import tmall.model.entity.FollowShop;
import tmall.model.entity.Shop;
import tmall.model.entity.ShoppingCart;
import tmall.model.entityDao.daoInterface.FavoriteShopDao;
import tmall.model.logicalEntity.FollowShopLogic;
import tmall.model.logicalEntity.ShoppingCartLogic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FavoriteShopDaoImpl implements FavoriteShopDao {

    // 店铺关注上下文
    private final XMLContext<FollowShop> followShopXMLContext = new ProxyXmlContext<>(FollowShop.class);

    private final XMLContext<Shop> shopXMLContext = new ProxyXmlContext<>(Shop.class);

    private FollowShop followShop;

    /**
     * 关注店铺
     *
     * @param shopId  店铺id
     * @param buyerId 买家id
     * @return 一个简单的关注信息
     */
    @Override
    public FollowShop create(String shopId, String buyerId) {
        // get the current date and time
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        followShop = new FollowShop(shopId, buyerId, dateTime.format(formatter));
        return followShop;
    }

    /**
     * 存储到数据库中
     */
    @Override
    public void addToDb() {
        followShopXMLContext.add(followShop);
    }

    /**
     * 获取买家关注店铺的详细信息
     *
     * @param buyerId 买家id
     * @return list shop details
     */
    @Override
    public List<FollowShopLogic> getByBuyerId(String buyerId) {

        List<FollowShopLogic> followShopLogicList = new ArrayList<>();
        // 获取购物车联系集所有数据
        List<FollowShop> init = followShopXMLContext.init();
        for (FollowShop fs : init) {
            if(fs.getBuyerId().equals(buyerId)){
                Shop shop = shopXMLContext.findById(fs.getShopId());
                FollowShopLogic followShopLogic = new FollowShopLogic(shop.getShopId(), shop.getShopName(), shop.getCreditScore(), shop.getCategory(), shop.getDescription(), fs.getFollowDate());
                followShopLogicList.add(followShopLogic);
            }
        }
        return followShopLogicList;
    }
}
