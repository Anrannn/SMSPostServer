package ltd.nanoda.service;

import ltd.nanoda.model.Message;

import java.util.List;

public interface MessageService {
    int insertMsg(Message message);
    Message getNewMsg();
    List<Message> getAllMsg();
}
