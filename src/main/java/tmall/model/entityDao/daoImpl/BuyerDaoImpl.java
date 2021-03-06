package tmall.model.entityDao.daoImpl;

import tmall.XMLRepository.ProxyXmlContext;
import tmall.XMLRepository.XMLContext;
import tmall.model.entity.Buyer;
import tmall.model.entityDao.daoInterface.BuyerDao;

import java.util.List;
import java.util.UUID;

public class BuyerDaoImpl implements BuyerDao {

    /**
     * 数据库上下文
     */
    private final XMLContext<Buyer> buyerXMLContext = new ProxyXmlContext<>(Buyer.class);

    private Buyer buyer;


    /**
     * 买家注册
     *
     * @param passwd   密码
     * @param idNumber 身份证号
     * @param phone    电话号码
     * @param nickname 昵称
     * @param gender   性别
     * @param birthday 生日
     * @return a Buyer
     */
    @Override
    public Buyer create(String passwd, String idNumber, String phone, String nickname, String gender, String birthday) {
        String buyerId = UUID.randomUUID().toString();
        buyer = new Buyer(buyerId, passwd, idNumber, phone, nickname, gender, birthday);
        return buyer;
    }

    /**
     * 存储到数据库中
     */
    @Override
    public void addToDb() {
        buyerXMLContext.add(buyer);
    }

    /**
     * 买家登录
     *
     * @param phone    电话号码
     * @param password 密码
     * @return 登录成功与否
     */
    @Override
    public boolean login(String phone, String password) {
        List<Buyer> init = buyerXMLContext.init();
        for (Buyer buyer1 : init) {
            if(buyer1.getPhone().equals(phone) && buyer1.getPasswd().equals(password))
                return true;
        }
        return false;
    }
}
