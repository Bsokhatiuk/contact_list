package app.service;


import app.model.Record;
import app.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void saveRecord(Record record);
    User findByUsername(String username);
    List<Record> findByUser(User user);

    void deleteuser(User user);

    void deleteRecord(Record record);
    Record findRecord(Long id);
    Record findRecord(User user,Long id);
}
