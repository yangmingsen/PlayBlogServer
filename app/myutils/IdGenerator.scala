package myutils
import java.util.concurrent.ThreadLocalRandom;

object IdGenerator {

  /**
   * 生成15位的唯一id
   * 时间戳+2位(1-99)的数字
   */
  def getTagId: Long = System.currentTimeMillis()*1000+(ThreadLocalRandom.current().nextInt(999) + 1).toLong

  /**
   * 生成16位的唯一id
   * 时间戳+3位(1-999)的数字
   */
  def getArticleId: Long = System.currentTimeMillis()*1000+(ThreadLocalRandom.current().nextInt(999) + 1).toLong

}
