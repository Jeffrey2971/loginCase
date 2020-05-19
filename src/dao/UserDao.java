package dao;

/*
    操作数据库中User表类
 */

import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class UserDao {

    // 声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 登录方法
     * @param loginUser 用户名和密码
     * @return user包含用户全部数据，没有查询到返回null
     */

    public User login(User loginUser){
        try {
            // 编写SQL
            String sql = "select * from user where username = ? and password = ?";
            // 调用query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace(); // 记录日志
            return null;
        }
    }

}
