package ltd.nanoda.service;

import ltd.nanoda.dao.UserDao;
import ltd.nanoda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
    @Autowired
    UserDao userDao;

    @Override
    public int checkUserInfo(User user) {
        User userdb =  userDao.getUserNameAndPassWd().get(0);
        if (user.getUserName().equals(userdb.getUserName())&&user.getPassWd().equals(userdb.getPassWd())){
            return 1;
        }else {
            return -1;
        }


    }
}
