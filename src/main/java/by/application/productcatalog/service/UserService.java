package by.application.productcatalog.service;

import by.application.productcatalog.model.entity.User;
import by.application.productcatalog.model.enums.AccessModeType;
import by.application.productcatalog.model.enums.UserType;
import by.application.productcatalog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import static by.application.productcatalog.model.enums.AccessModeType.LOGIN;
import static by.application.productcatalog.model.enums.AccessModeType.LOGOUT;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //список всех пользователей
    public List<User> getAllUsers(){
        Iterable<User> users = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        return userList;
    }

    public Optional<User> login(String login, String password){
        Optional<User> optionalUser = userRepository.findByLoginAndPassword(login, password);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setAccessMode(LOGIN.getValue());
            userRepository.save(user);
        }
        return optionalUser;
    }

    public void register(String name, String login, String password, String mail, String info){
        int id = (userRepository.findMaxId() == null) ? 1: userRepository.findMaxId()+1;
        User user = new User(id, UserType.USER.getValue(), name, login, password, mail, info, LOGOUT.getValue());
        userRepository.save(user);
    }

    public boolean findAuthorizationUser(){
        Optional<User> optionalUser = userRepository.findByAccessMode(LOGIN.getValue());
        return optionalUser.isPresent();
    }

    public Optional<User> getAuthUser(){
        return userRepository.findByAccessMode(LOGIN.getValue());
    }

    public boolean checkLogin(String login){
        Optional<User> optionalUser = userRepository.findByLogin(login);
        return optionalUser.isPresent();
    }

    public Optional<User> findUserById(Integer userId){
        return userRepository.findById(userId);
    }

    public void logout(){
        Optional<User> optionalUser = getAuthUser();
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setAccessMode(LOGOUT.getValue());
            userRepository.save(user);
        }
    }

    public void update(String name, String login, String password){
        Optional<User> optionalUser = getAuthUser();
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setName(name);
            user.setLogin(login);
            user.setPassword(password);
            userRepository.save(user);
        }
    }

}
