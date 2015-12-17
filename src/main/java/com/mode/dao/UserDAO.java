package com.mode.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.mode.domain.Authority;
import com.mode.domain.User;

/**
 * Created by chao on 11/18/15.
 */
public interface UserDAO {

    /**
     * Find a user by its unique email.
     *
     * @param email unique email
     * @return user
     */
    @Select("SELECT * FROM user WHERE email = #{email}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "activated", column = "activated"),
            @Result(property = "activationKey", column = "activationkey"),
            @Result(property = "resetPasswordKey", column = "resetpasswordkey"),
            @Result(property = "authorities", javaType = List.class, column = "id",
                    many = @Many(select = "listAuthoritiesByUserId"))
    })
    User findByEmail(String email);

    /**
     * Find a user by its unique username.
     *
     * @param username unique username
     * @return user
     */
    @Select("SELECT * FROM user WHERE username = #{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "activated", column = "activated"),
            @Result(property = "activationKey", column = "activationkey"),
            @Result(property = "resetPasswordKey", column = "resetpasswordkey"),
            @Result(property = "authorities", javaType = List.class, column = "id",
                    many = @Many(select = "listAuthoritiesByUserId"))
    })
    User findByUsername(String username);

    /**
     * Find a list of roles that the specific user has.
     *
     * Note: since database table column name maps directly to its corresponding java entity field, there's no need
     * to provide any column-to-property mapping using the @Result annotation.
     *
     * @param id user id
     * @return a list of roles for the specific user
     */
    @Select("SELECT a.* FROM user_authority ua, authority a WHERE ua.authority_id = a.id AND ua.user_id = #{id}")
    List<Authority> listAuthoritiesByUserId(Integer id);
}