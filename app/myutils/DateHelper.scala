package myutils

import java.text.SimpleDateFormat
import java.util.Date

object DateHelper {
  val P1 = "yyyy-MM-dd"
  val P2 = "yyyy-MM-dd HH:mm"
  val P3 = "yyyy-MM"
  val P4 = "yyyy-MM-dd HH:mm:ss"
  val P5 = "yyyy.MM.dd HH:mm:ss"
  val P6 = "yyyy年MM月dd日HH时"

  /***
   * format pattern
   * 1. "yyyy-MM-dd"
   * 2. "yyyy-MM-dd HH:mm"
   * 3. "yyyy-MM"
   * 4. "yyyy-MM-dd HH:mm:ss"
   * 5. "yyyy年MM月dd日HH时"
   * @param format
   * @return
   */
  def get(format: String = P4): String = sdf(format)

  private def sdf(format: String) = new SimpleDateFormat(format).format(new Date())

  /***
   * get "yyyy-MM-dd"
   * @return
   */
  def getT1 = sdf(P1)

  /***
   * get "yyyy-MM-dd HH:mm"
   * @return
   */
  def getT2 = sdf(P2)

  /***
   * get "yyyy-MM"
   * @return
   */
  def getT3 = sdf(P3)

  /***
   * get "yyyy.MM.dd HH:mm:ss"
   * @return
   */
  def getT5 = sdf(P5)

  /***
   * get "yyyy年MM月dd日HH时"
   * @return
   */
  def getT6 = sdf(P6)
}

