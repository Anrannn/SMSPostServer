package ltd.nanoda.dao;

import ltd.nanoda.model.Message;

import java.util.List;

public interface MessageDao {
    int insertMsg(String phoneNumber, String content);
    Message getNewMsg();
    List<Message> getAllMsg();
}
