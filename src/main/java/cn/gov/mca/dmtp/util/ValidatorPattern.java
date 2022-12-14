package cn.gov.mca.dmtp.util;

public class ValidatorPattern {
  /**
   * 正则表达式：日期格式，yyyy-MM-dd
   */
  public static final String REGEX_YMD = "[1-2][0-9]{3}(-)\\d{1,2}\\1\\d{1,2}";

  /**
   * 正则表达式：验证密码
   * 密码只能为 8 - 20位数字，字母及常用符号组成。
   */
  public static final String REGEX_PASSWORD = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9._~!@#$^&*]{8,20}$";

  /**
   * 正则表达式：验证手机号
   */
  public static final String REGEX_MOBILE = "^[1][34578]\\d{9}$";

  /**
   * 正则表达式：验证邮箱
   */
  public static final String REGEX_EMAIL = "^.+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

  /**
   * 正则表达式：验证汉字
   */
  public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],*$";

  /**
   * 正则表达式：验证身份证
   */
  public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

  /**
   * 正则表达式：验证URL
   */
  public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

  /**
   * 正则表达式：验证IP地址
   */
  public static final String REGEX_IP_ADDRESS = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

  /**
   * 车牌号正则
   */
  public static final String LICENSE_NO = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z][A-Z][A-Z0-9]{4,5}[A-Z0-9挂学警港澳]$";

  /**
   * 姓名校验
   * 1~15位
   * 姓名支持空格和中文的点
   */
  public static final String NAME = "[\u4e00-\u9fa5\u00b7\sA-Za-z]{1,15}$";

  /**
   * 表情正则
   */
  public static final String EMOJI = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";

  /**
   * 数字正则
   */
  public static final String NUMBER = "^[0-9]*$";

  /**
   * n位的数字
   */
  public static final String N_NUMBERS = "^\\d{n}$";
}
