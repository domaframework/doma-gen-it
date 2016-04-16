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
package tmp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import example.config.Container;
import example.config.Dbms;
import example.config.Run;
import example.config.Sandbox;
import example.dao.Table0Dao;
import example.dao.Table0DaoImpl;
import example.entity.Table0;

/**
 * @author matsumana
 */
@Run(onlyIf = Dbms.MYSQL)
public class DbAccessTest {

    @ClassRule
    public static Container container = new Container();

    @Rule
    public Sandbox sandbox = new Sandbox(container);

    /**
     * INSERTしたデータがSELECTできるかテスト
     */
    @Test
    public void insertAndSelectTest() {
        Table0Dao dao = container.get(Table0DaoImpl::new);

        // INSERT
        Table0 entity = new Table0();
        entity.setColBitNone(true);
        entity.setColBit1(false);
        entity.setColBit2(Byte.valueOf("1"));
        entity.setColTinyintNone(Byte.valueOf("2"));
        entity.setColTinyint1(true);
        entity.setColTinyint2(Byte.valueOf("3"));
        entity.setColTinyintUnsignedNone(Short.valueOf("4"));
        entity.setColTinyintUnsigned1(false);
        entity.setColTinyintUnsigned2(Short.valueOf("5"));
        entity.setColBool(true);
        entity.setColBoolean(false);
        entity.setColSmallint(Short.valueOf("6"));
        entity.setColSmallintUnsigned(7);
        entity.setColMediumint(8);
        entity.setColMediumintUnsigned(9);
        entity.setColInt(10);
        entity.setColIntUnsigned(11L);
        entity.setColInteger(12);
        entity.setColIntegerUnsigned(13L);
        entity.setColBigint(14L);
        entity.setColBigintUnsigned(new BigInteger("15"));
        entity.setColDecimal(new BigDecimal("16"));
        entity.setColDecimalUnsigned(new BigDecimal("17"));
        entity.setColDec(new BigDecimal("18"));
        entity.setColDecUnsigned(new BigDecimal("19"));
        entity.setColFloat(Float.valueOf(20));
        entity.setColFloatUnsigned(Float.valueOf(21));
        entity.setColDouble(Double.valueOf(22));
        entity.setColDoubleUnsigned(Double.valueOf(23));
        entity.setColDoublePrecision(Double.valueOf(24));
        entity.setColDoublePrecisionUnsigned(Double.valueOf(25));
        entity.setColDate(LocalDate.of(2016, 4, 4));
        entity.setColDatetime(LocalDateTime.of(2016, 4, 5, 12, 34, 56));
        entity.setColTimestamp(LocalDateTime.of(2016, 4, 6, 12, 34, 57));
        entity.setColTime(LocalTime.of(12, 34, 58));
        entity.setColYear(Short.valueOf("2016"));
        entity.setColBinary("ABCabc".getBytes());
        entity.setColVarbinary("DEF".getBytes());
        entity.setColChar("char!");
        entity.setColVarchar("varchar!");
        entity.setColTinytext("tinytext!");
        entity.setColText("text!");
        entity.setColMediumtext("mediumtext!");
        entity.setColLongtext("longtext!");
        entity.setColEnum("HOGE");
        dao.insert(entity);

        // SELECT
        Table0 actual = dao.selectById(new BigInteger("1"));

        // assert
        assertThat(actual.getColBitNone(), is(true));
        assertThat(actual.getColBit1(), is(false));
        assertThat(actual.getColBit2(), is(Byte.valueOf("1")));
        assertThat(actual.getColTinyintNone(), is(Byte.valueOf("2")));
        assertThat(actual.getColTinyint1(), is(true));
        assertThat(actual.getColTinyint2(), is(Byte.valueOf("3")));
        assertThat(actual.getColTinyintUnsignedNone(), is(Short.valueOf("4")));
        assertThat(actual.getColTinyintUnsigned1(), is(false));
        assertThat(actual.getColTinyintUnsigned2(), is(Short.valueOf("5")));
        assertThat(actual.getColBool(), is(true));
        assertThat(actual.getColBoolean(), is(false));
        assertThat(actual.getColSmallint(), is(Short.valueOf("6")));
        assertThat(actual.getColSmallintUnsigned(), is(7));
        assertThat(actual.getColMediumint(), is(8));
        assertThat(actual.getColMediumintUnsigned(), is(9));
        assertThat(actual.getColInt(), is(10));
        assertThat(actual.getColIntUnsigned(), is(11L));
        assertThat(actual.getColInteger(), is(12));
        assertThat(actual.getColIntegerUnsigned(), is(13L));
        assertThat(actual.getColBigint(), is(14L));
        assertThat(actual.getColBigintUnsigned(), is(new BigInteger("15")));
        assertThat(actual.getColDecimal(), is(new BigDecimal("16")));
        assertThat(actual.getColDecimalUnsigned(), is(new BigDecimal("17")));
        assertThat(actual.getColDec(), is(new BigDecimal("18")));
        assertThat(actual.getColDecUnsigned(), is(new BigDecimal("19")));
        assertThat(actual.getColFloat(), is(Float.valueOf(20)));
        assertThat(actual.getColFloatUnsigned(), is(Float.valueOf(21)));
        assertThat(actual.getColDouble(), is(Double.valueOf(22)));
        assertThat(actual.getColDoubleUnsigned(), is(Double.valueOf(23)));
        assertThat(actual.getColDoublePrecision(), is(Double.valueOf(24)));
        assertThat(actual.getColDoublePrecisionUnsigned(),
                is(Double.valueOf(25)));
        assertThat(actual.getColDate(), is(LocalDate.of(2016, 4, 4)));
        assertThat(actual.getColDate(), is(LocalDate.of(2016, 4, 4)));
        assertThat(actual.getColDatetime(),
                is(LocalDateTime.of(2016, 4, 5, 12, 34, 56)));
        assertThat(actual.getColTimestamp(),
                is(LocalDateTime.of(2016, 4, 6, 12, 34, 57)));
        assertThat(actual.getColTime(), is(LocalTime.of(12, 34, 58)));
        assertThat(actual.getColYear(), is(Short.valueOf("2016")));
        assertThat(actual.getColBinary(), is("ABCabc".getBytes()));
        assertThat(actual.getColVarbinary(), is("DEF".getBytes()));
        assertThat(actual.getColChar(), is("char!"));
        assertThat(actual.getColVarchar(), is("varchar!"));
        assertThat(actual.getColTinytext(), is("tinytext!"));
        assertThat(actual.getColText(), is("text!"));
        assertThat(actual.getColMediumtext(), is("mediumtext!"));
        assertThat(actual.getColLongtext(), is("longtext!"));
        assertThat(actual.getColEnum(), is("HOGE"));
    }
}
