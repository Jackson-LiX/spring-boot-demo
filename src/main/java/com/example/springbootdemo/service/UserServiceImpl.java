package com.example.springbootdemo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootdemo.mapper.UserModelMapper;
import com.example.springbootdemo.model.UserModel;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class UserServiceImpl implements UserService {

    private final UserModelMapper userModelMapper;

    private final RedisTemplate<String, Object> redisTemplate;

    public UserServiceImpl(UserModelMapper userModelMapper,
                           RedisTemplate<String, Object> redisTemplate) {
        this.userModelMapper = userModelMapper;
        this.redisTemplate = redisTemplate;
    }

    /**
     * (non-Javadoc)
     *
     * @see UserService#getAllUser()
     */
    @Override
    public List<UserModel> getAllUser() {
        return userModelMapper.selectList(null);
    }

    /**
     * (non-Javadoc)
     *
     * @see UserService#addUserToRedis(UserModel)
     */
    @Override
    public void addUserToRedis(UserModel userModel) {
        this.redisTemplate.opsForValue().set(String.valueOf(userModel.getUserName()), userModel, 1, TimeUnit.MINUTES);
    }

    /**
     * (non-Javadoc)
     *
     * @see UserService#getUserFromRedisByUserName(String)
     */
    @Override
    public UserModel getUserFromRedisByUserName(String userName) {
        return (UserModel) this.redisTemplate.opsForValue().get(userName);
    }

    /**
     * (non-Javadoc)
     *
     * @see UserService#removeUserFromRedisByUserName(String)
     */
    @Override
    public void removeUserFromRedisByUserName(String userName) {
        this.redisTemplate.delete(userName);
    }

    @Override
    public boolean saveBatch(Collection<UserModel> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<UserModel> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<UserModel> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(UserModel entity) {
        return false;
    }

    @Override
    public UserModel getOne(Wrapper<UserModel> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<UserModel> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<UserModel> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<UserModel> getBaseMapper() {
        return userModelMapper;
    }

    @Override
    public Class<UserModel> getEntityClass() {
        return UserModel.class;
    }
}
