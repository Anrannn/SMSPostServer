package ltd.nanoda.dao;

import ltd.nanoda.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUserNameAndPassWd();
}
