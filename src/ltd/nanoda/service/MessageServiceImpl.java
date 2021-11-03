package ltd.nanoda.service;

import ltd.nanoda.dao.MessageDao;
import ltd.nanoda.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService{
    @Autowired
    MessageDao messageDao;

    @Override
    public int insertMsg(Message message) {
        return messageDao.insertMsg(message.getPhoneNumber(), message.getContent());
    }

    @Override
    public Message getNewMsg() {
        return messageDao.getNewMsg();
    }

    @Override
    public List<Message> getAllMsg() {
        return messageDao.getAllMsg();
    }
}
