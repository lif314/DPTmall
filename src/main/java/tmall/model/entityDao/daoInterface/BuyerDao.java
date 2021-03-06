package tmall.model.entityDao.daoInterface;

import tmall.model.entity.Buyer;

public interface BuyerDao {


    /**
     * 买家注册
     * @param passwd 密码
     * @param idNumber 身份证号
     * @param phone 电话号码
     * @param nickname 昵称
     * @param gender 性别
     * @param birthday 生日
     * @return a Buyer
     */
    Buyer create(String passwd, String idNumber, String phone, String nickname, String gender, String birthday);

    /**
     * 存储到数据库中
     */
    void addToDb();

    /**
     * 买家登录
     * @param phone 电话号码
     * @param password 密码
     * @return 登录成功与否
     */
    boolean login(String phone, String password);
}
