package com.budgetify.dao;

import com.budgetify.dto.AccountSaveDto;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class AccountDao extends BaseAccountDao {
    public AccountDao(DataSource dataSource) {
        super(dataSource);
    }

    public int save(AccountSaveDto accountSaveDto) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("accounts")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("user_id", accountSaveDto.getUserId());
        parameters.put("name", accountSaveDto.getName());
        parameters.put("balance", accountSaveDto.getBalance());
        parameters.put("created_at", accountSaveDto.getCreatedAt());
        parameters.put("currency_id", accountSaveDto.getCurrencyId());

        Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);

        return generatedId.intValue();
    }
}
