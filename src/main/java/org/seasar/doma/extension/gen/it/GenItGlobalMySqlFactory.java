/*
 * Copyright 2004-2016 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.extension.gen.it;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.seasar.doma.extension.gen.GlobalFactory;
import org.seasar.doma.extension.gen.internal.util.JdbcUtil;

/** @author matsumana */
public class GenItGlobalMySqlFactory extends GlobalFactory {

  @Override
  public DataSource createDataSource(Driver driver, String user, String password, String url) {
    DataSource dataSource = super.createDataSource(driver, user, password, url);
    Connection connection = JdbcUtil.getConnection(dataSource);
    try {
      Statement statement = JdbcUtil.createStatement(connection);
      try {
        statement.executeUpdate("DROP TABLE IF EXISTS table_0");
        statement.executeUpdate(
            "CREATE TABLE table_0"
                + "("
                + "  col_serial SERIAL PRIMARY KEY NOT NULL,"
                + "  col_bit_none BIT,"
                + "  col_bit_1 BIT(1),"
                + "  col_bit_2 BIT(2),"
                + "  col_tinyint_none TINYINT,"
                + "  col_tinyint_1 TINYINT(1),"
                + "  col_tinyint_2 TINYINT(2),"
                + "  col_tinyint_unsigned_none TINYINT UNSIGNED,"
                + "  col_tinyint_unsigned_1 TINYINT(1) UNSIGNED,"
                + "  col_tinyint_unsigned_2 TINYINT(2) UNSIGNED,"
                + "  col_bool BOOL,"
                + "  col_boolean BOOL,"
                + "  col_smallint SMALLINT,"
                + "  col_smallint_unsigned SMALLINT UNSIGNED,"
                + "  col_mediumint MEDIUMINT,"
                + "  col_mediumint_unsigned MEDIUMINT UNSIGNED,"
                + "  col_int INT,"
                + "  col_int_unsigned INT UNSIGNED,"
                + "  col_integer INT,"
                + "  col_integer_unsigned INT UNSIGNED,"
                + "  col_bigint BIGINT,"
                + "  col_bigint_unsigned BIGINT UNSIGNED,"
                + "  col_decimal DECIMAL,"
                + "  col_decimal_unsigned DECIMAL UNSIGNED,"
                + "  col_dec DECIMAL,"
                + "  col_dec_unsigned DECIMAL UNSIGNED,"
                + "  col_float FLOAT,"
                + "  col_float_unsigned FLOAT UNSIGNED,"
                + "  col_double DOUBLE,"
                + "  col_double_unsigned DOUBLE UNSIGNED,"
                + "  col_double_precision DOUBLE,"
                + "  col_double_precision_unsigned DOUBLE UNSIGNED,"
                + "  col_date DATE,"
                + "  col_datetime DATETIME,"
                + "  col_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,"
                + "  col_time TIME,"
                + "  col_year YEAR,"
                + "  col_tinyblob TINYBLOB,"
                + "  col_blob BLOB,"
                + "  col_mediumblob MEDIUMBLOB,"
                + "  col_longblob LONGBLOB,"
                + "  col_binary BINARY(6),"
                + "  col_varbinary VARBINARY(32),"
                + "  col_char CHAR(32),"
                + "  col_varchar VARCHAR(32),"
                + "  col_tinytext TINYTEXT,"
                + "  col_text TEXT,"
                + "  col_mediumtext MEDIUMTEXT,"
                + "  col_longtext LONGTEXT,"
                + "  col_enum ENUM('HOGE', 'FUGA', 'PIYO')"
                + ")");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      } finally {
        JdbcUtil.close(statement);
      }
    } finally {
      JdbcUtil.close(connection);
    }

    return dataSource;
  }
}
