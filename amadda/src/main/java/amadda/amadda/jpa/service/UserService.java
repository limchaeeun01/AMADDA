package amadda.amadda.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import amadda.amadda.jpa.dao.UserDAO;
import amadda.amadda.jpa.domain.entity.UserRequestDTO;

@Service
public class UserService {

    @Autowired
    private UserDAO userDao;

    public UserRequestDTO save(UserRequestDTO params) {
        System.out.println("UserService save: " + params);
        return userDao.save(params);
    }

}
