package kaszucar.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kaszucar.util.Util;

public class UtilTest {

  String str;
  String str1;
  String str2;
  String str3;
  String str4;
  String str5;
  String str6;
  String str7;
  String str8;
  String str9;


  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  @Before
  public void setUp() throws Exception {
    str = "test";
    str1 = "";
    str2 = "null";
    str3 = null;
    str4 = "undefined";
    str5 = "nullundefined";
    str6 = "2";
    str7 = "2.2";
    str8 = "2,2";
    str9 = "10,607452";
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void convertToIntTest() {
    assertFalse(Util.convertToInt(str));
    assertFalse(Util.convertToInt(str1));
    assertFalse(Util.convertToInt(str2));
    assertFalse(Util.convertToInt(str3));
    assertFalse(Util.convertToInt(str4));
    assertFalse(Util.convertToInt(str5));
    assertTrue(Util.convertToInt(str6));
    assertFalse(Util.convertToInt(str7));
    assertFalse(Util.convertToInt(str8));
    assertFalse(Util.convertToInt(str9));
  }


  @Test
  public void convertToShortTest() {
    assertFalse(Util.convertToShort(str));
    assertFalse(Util.convertToShort(str1));
    assertFalse(Util.convertToShort(str2));
    assertFalse(Util.convertToShort(str3));
    assertFalse(Util.convertToShort(str4));
    assertFalse(Util.convertToShort(str5));
    assertTrue(Util.convertToShort(str6));
    assertFalse(Util.convertToShort(str7));
    assertFalse(Util.convertToShort(str8));
    assertFalse(Util.convertToShort(str9));
  }

  @Test
  public void stringIsNullTest() {
    assertFalse(Util.stringIsNull(str));
    assertTrue(Util.stringIsNull(str1));
    assertTrue(Util.stringIsNull(str2));
    assertTrue(Util.stringIsNull(str3));
    assertTrue(Util.stringIsNull(str4));
    assertFalse(Util.stringIsNull(str5));
    assertFalse(Util.stringIsNull(str6));
    assertFalse(Util.stringIsNull(str7));
    assertFalse(Util.stringIsNull(str8));
    assertFalse(Util.stringIsNull(str9));
  }


  @Test
  public void stringIsNotNullTest() {
    assertTrue(Util.stringIsNotNull(str));
    assertFalse(Util.stringIsNotNull(str1));
    assertFalse(Util.stringIsNotNull(str2));
    assertFalse(Util.stringIsNotNull(str3));
    assertFalse(Util.stringIsNotNull(str4));
    assertTrue(Util.stringIsNotNull(str5));
    assertTrue(Util.stringIsNotNull(str6));
    assertTrue(Util.stringIsNotNull(str7));
    assertTrue(Util.stringIsNotNull(str8));
    assertTrue(Util.stringIsNotNull(str9));
  }

  @Test
  public void isPriceTest() {
    assertFalse(Util.isPrice(str));
    assertFalse(Util.isPrice(str1));
    assertFalse(Util.isPrice(str2));
    assertFalse(Util.isPrice(str3));
    assertFalse(Util.isPrice(str4));
    assertFalse(Util.isPrice(str5));
    assertTrue(Util.isPrice(str6));
    assertTrue(Util.isPrice(str7));
    assertTrue(Util.isPrice(str8));
    assertFalse(Util.isPrice(str9));
  }

  @Test
  public void getDateByParamTest() throws ParseException {
    Date date = Util.getDateByParam("1992-10-26", "11", "11");
    Date date1 = Util.getDateByParam("1992/10/26", "11", "11");
    Date date2 = Util.getDateByParam("01563", "10", "15");
    Date date3 = Util.getDateByParam("1992/10/26", "october", "11");
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    assertEquals(sdf.parse("1992/10/26 11:11"), date);
    assertEquals(sdf.parse("1992/10/26 11:11"), date1);
    assertEquals(null, date2);
    assertEquals(null, date3);
  }


}
