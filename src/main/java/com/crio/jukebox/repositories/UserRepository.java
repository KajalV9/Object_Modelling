package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository {

    private final Map<String,User> userMap;
    private Integer autoIncrement = 0;
    
    public UserRepository() {
        userMap = new HashMap<String,User>();
        this.autoIncrement = userMap.size();
    }

    

    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
    }



    @Override
    public User save(User entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            User u = new User(Integer.toString(autoIncrement),entity.getName());
            userMap.put(u.getId(),u);
            return u;
        }
        userMap.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());

    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
       if(userMap.containsKey(id))
       return true;
       else
       return false;
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }



   


    
}
