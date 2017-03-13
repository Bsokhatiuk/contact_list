package app.service;

import app.WebApplication;
import app.model.Record;
import app.model.User;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebApplication.class)
public class TestUserService {
    @Autowired
    private UserService userService;

    private User user;
    private Record recordTest;

    @Before
    public void init() {
        user = new User("Test", "Test", "Pass1234");
        userService.save(user);

        recordTest = new Record("Demo", "Demo", "Demo", "+380(98) 000-00-00");
        recordTest.setUser(user);
        userService.saveRecord(recordTest);
    }

    @Test
    public void TestAddRecord() {
        Assert.assertThat(userService.findByUser(user).size(), Matchers.greaterThan(0));
    }

    @Test
    public void TestDeleteRecord() {
        Record record = new Record("Demo2", "Demo2", "Demo2", "+380(98) 000-00-00");
        record.setUser(user);
        userService.saveRecord(record);
        userService.deleteRecord(record);
        Assert.assertThat(userService.findByUser(user).size(), Matchers.equalTo(1));
    }


}
