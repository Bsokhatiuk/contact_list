package app.dao;


import app.WebApplication;
import app.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebApplication.class)
public class TestUserRepository {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsertUser() {
        User user = new User("Test", "Test", "Pass1234");
        userRepository.save(user);
        Assert.assertEquals(user.getName(), userRepository.findByUsername(user.getUsername()).getName());
    }


}
