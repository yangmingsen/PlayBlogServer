package myimpli

import models._
import play.api.libs.json.{Format, Json}

object MyImplicit {
  implicit val tagFormats: Format[Tag] = Json.format[Tag]
  implicit val articleFormats: Format[Article] = Json.format[Article]
  implicit val resultFormats: Format[Result] = Json.format[Result]
  implicit val categoryFormats: Format[Category] = Json.format[Category]
  implicit val commentFormats: Format[Comment] = Json.format[Comment]
}
