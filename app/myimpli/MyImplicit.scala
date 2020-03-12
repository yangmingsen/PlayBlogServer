package myimpli

import models._
import play.api.libs.json.{Format, Json}

object MyImplicit {
  implicit val tagWrites: Format[Tag] = Json.format[Tag]
  implicit val articleWrites: Format[Article] = Json.format[Article]
  implicit val resultWrites: Format[Result] = Json.format[Result]
  implicit val categoryWrites: Format[Category] = Json.format[Category]
}
