package tmall.model.entityDao.daoInterface;

import tmall.model.entity.Seller;

public interface SellerDao {

    /**
     * 创建卖家--注册， 注册后一定要手动添加到数据库中
     * @param password 密码
     * @param name 真实姓名
     * @param idNumber 身份证号码
     * @param nickname 昵称
     * @param phone 手机号
     * @return a Seller
     */
    Seller create(String password, String name, String idNumber, String nickname, String phone);

    /**
     * 存储到数据库中
     */
    void addToDb();

    /**
     * 卖家登录
     * @param name 真实姓名
     * @param password 密码
     * @return 账号信息是否正确
     */
    Boolean login(String name, String password);
}
