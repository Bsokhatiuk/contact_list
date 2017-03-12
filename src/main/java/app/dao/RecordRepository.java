package app.dao;

import app.model.Record;
import app.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecordRepository extends CrudRepository<Record,Long> {
    List<Record> findByUser(User user);
    Record findByUserAndId(User user, Long id);
}
