package ltd.nanoda.dao;

import ltd.nanoda.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("messageDao")
public class MessageDaoImpl implements MessageDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertMsg(String phoneNumber, String content) {
        String sql = "insert into smstable(phoneNumber,content,date) value(?,?,NOW())";
        jdbcTemplate.update(sql, phoneNumber, content);
        return 0;
    }

    @Override
    public Message getNewMsg() {
        String sql="SELECT * FROM smstable WHERE id=(SELECT MAX(id) FROM smstable);";
        RowMapper<Message> rowMapper = new BeanPropertyRowMapper<>(Message.class);
        List<Message> messageList = jdbcTemplate.query(sql,rowMapper);
        return messageList.get(0);
    }

    @Override
    public List<Message> getAllMsg() {
        String sql = "select * from smstable";
        RowMapper<Message> rowMapper = new BeanPropertyRowMapper<>(Message.class);
        return jdbcTemplate.query(sql,rowMapper);
    }
}
