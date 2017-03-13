package app.service;


import app.dao.RecordRepository;
import app.dao.RoleRepository;
import app.dao.UserRepository;
import app.model.Record;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public void saveRecord(Record record) {
        recordRepository.save(record);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Record> findByUser(User user) {
        return recordRepository.findByUser(user);
    }

    @Override
    public void deleteRecord(Record record) {
        recordRepository.delete(record);
    }

    @Override
    public Record findRecord(Long id) {
        return recordRepository.findOne(id);
    }

    @Override
    public Record findRecord(User user, Long id) {
        return recordRepository.findByUserAndId(user, id);
    }
}
