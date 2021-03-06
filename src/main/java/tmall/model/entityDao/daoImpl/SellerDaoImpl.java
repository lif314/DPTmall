package tmall.model.entityDao.daoImpl;

import tmall.XMLRepository.ProxyXmlContext;
import tmall.XMLRepository.XMLContext;
import tmall.model.entity.Seller;
import tmall.model.entityDao.daoInterface.SellerDao;

import java.util.List;
import java.util.UUID;

public class SellerDaoImpl implements SellerDao {
    /**
     * 数据库上下文
     */
    private final XMLContext<Seller> sellerXMLContext = new ProxyXmlContext<>(Seller.class);

    /**
     * 数据映射模式 + 数据访问访问模式
     */
    private Seller seller;

    /**
     * 创建卖家--注册， 注册后一定要手动添加到数据库中
     *
     * @param password 密码
     * @param name     真实姓名
     * @param idNumber 身份证号码
     * @param nickname 昵称
     * @param phone    手机号
     * @return a Seller
     */
    @Override
    public Seller create(String password, String name, String idNumber, String nickname, String phone) {

        UUID sellerId = UUID.randomUUID();
        seller = new Seller(sellerId.toString(), password, name, idNumber, nickname, phone);
        return seller;
    }

    /**
     * 存储到数据库中
     */
    @Override
    public void addToDb() {
        sellerXMLContext.add(seller);
    }

    /**
     * 卖家登录
     *
     * @param name     真实姓名
     * @param password 密码
     * @return 账号信息是否正确
     */
    @Override
    public Boolean login(String name, String password) {

        List<Seller> all = sellerXMLContext.init();
        for (Seller seller1 : all) {
            if(seller1.getName().equals(name) && seller1.getPassword().equals(password))
                return true;
        }

        return false;
    }
}
