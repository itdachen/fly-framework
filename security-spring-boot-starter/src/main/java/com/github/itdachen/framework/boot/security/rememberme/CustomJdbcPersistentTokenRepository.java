package com.github.itdachen.framework.boot.security.rememberme;

import org.springframework.core.log.LogMessage;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Description: 自定义 记住我 实现
 * 数据库结构在 db 文件下
 * Created by 剑鸣秋朔 on 2022-11-04 9:31
 * Created with IntelliJ IDEA.
 */
public class CustomJdbcPersistentTokenRepository extends JdbcDaoSupport implements PersistentTokenRepository {
    /* 记住我数据库表名 */
    private static final String REMEMBER_ME_TABLE_NAME = "ta_fly_persistent_logins";
    private static final String tokensBySeriesSql = "select username,series,token,last_used from " + REMEMBER_ME_TABLE_NAME + " where series = ?";
    private static final String insertTokenSql = "insert into " + REMEMBER_ME_TABLE_NAME + " (username, series, token, last_used) values(?,?,?,?)";
    private static final String updateTokenSql = "update " + REMEMBER_ME_TABLE_NAME + " set token = ?, last_used = ? where series = ?";
    private static final String removeUserTokensSql = "delete from " + REMEMBER_ME_TABLE_NAME + " where username = ?";
    public static final String CREATE_TABLE_SQL = "create table " + REMEMBER_ME_TABLE_NAME + " (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null)";
    public static final String DEF_TOKEN_BY_SERIES_SQL = "select username,series,token,last_used from " + REMEMBER_ME_TABLE_NAME + " where series = ?";
    public static final String DEF_INSERT_TOKEN_SQL = "insert into " + REMEMBER_ME_TABLE_NAME + " (username, series, token, last_used) values(?,?,?,?)";
    public static final String DEF_UPDATE_TOKEN_SQL = "update " + REMEMBER_ME_TABLE_NAME + " set token = ?, last_used = ? where series = ?";
    public static final String DEF_REMOVE_USER_TOKENS_SQL = "delete from " + REMEMBER_ME_TABLE_NAME + " where username = ?";

    private boolean createTableOnStartup;

    public CustomJdbcPersistentTokenRepository() {
    }

    protected void initDao() {
        if (this.createTableOnStartup) {
            this.getJdbcTemplate().execute("create table " + REMEMBER_ME_TABLE_NAME + " (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null)");
        }

    }

    public void createNewToken(PersistentRememberMeToken token) {
        this.getJdbcTemplate().update(this.insertTokenSql, new Object[]{token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate()});
    }

    public void updateToken(String series, String tokenValue, Date lastUsed) {
        this.getJdbcTemplate().update(this.updateTokenSql, new Object[]{tokenValue, lastUsed, series});
    }

    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            return (PersistentRememberMeToken) this.getJdbcTemplate().queryForObject(this.tokensBySeriesSql, this::createRememberMeToken, new Object[]{seriesId});
        } catch (EmptyResultDataAccessException var3) {
            this.logger.debug(LogMessage.format("Querying token for series '%s' returned no results.", seriesId), var3);
        } catch (IncorrectResultSizeDataAccessException var4) {
            this.logger.error(LogMessage.format("Querying token for series '%s' returned more than one value. Series should be unique", seriesId));
        } catch (DataAccessException var5) {
            this.logger.error("Failed to load token for series " + seriesId, var5);
        }

        return null;
    }

    private PersistentRememberMeToken createRememberMeToken(ResultSet rs, int rowNum) throws SQLException {
        return new PersistentRememberMeToken(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4));
    }

    public void removeUserTokens(String username) {
        this.getJdbcTemplate().update(this.removeUserTokensSql, new Object[]{username});
    }

    public void setCreateTableOnStartup(boolean createTableOnStartup) {
        this.createTableOnStartup = createTableOnStartup;
    }

}
