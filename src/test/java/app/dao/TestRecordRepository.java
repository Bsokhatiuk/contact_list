package app.dao;

import app.WebApplication;
import app.model.Record;
import app.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebApplication.class)
public class TestRecordRepository {

    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private UserRepository userRepository;

    private User user;
    private Record recordTest;


    @Before
    public void init() {
        user = userRepository.save(new User("Test", "Test", "Pass1234"));
        recordTest = new Record("Demo", "Demo", "Demo", "+380(98) 000-00-00");
        recordTest.setUser(user);
        Record addRecord = recordRepository.save(recordTest);
    }

    @Test
    public void testAddRecord() {
        Record record = new Record("Demo", "Demo", "Demo", "+380(98) 000-00-00");
        record.setUser(user);
        Record addRecord = recordRepository.save(record);
        Assert.assertEquals(record, recordRepository.findOne(addRecord.getId()));
        recordRepository.delete(addRecord);
    }

    @Test
    public void testFindRecordByUserAndId() {
        Assert.assertEquals(recordTest.getName(), recordRepository.findByUserAndId(user, recordTest.getId()).getName());
    }


    @Test
    public void testFindByUser() {
        Assert.assertEquals(1, recordRepository.findByUser(user).size());
    }
}
