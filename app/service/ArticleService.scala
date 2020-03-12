package service

import javax.inject.Singleton
import models.{Article, Result}
import play.api.libs.json.{JsValue, Json}
import myimpli.MyImplicit.{articleWrites, resultWrites}

@Singleton
class ArticleService {
  def findOne(id: Long): JsValue = {
    Article findOne(id) match {
      case Some(one) => Json.toJson(one)
      case None => Json.toJson(Result(false,"No entity"))
    }
  }
  def findAll: JsValue = Json.toJson(Article.findAll())
}
