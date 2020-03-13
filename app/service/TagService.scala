package service

import javax.inject.Singleton
import models.{Result, Tag}
import play.api.libs.json.{JsValue, Json}
import myimpli.MyImplicit.{resultFormats, tagFormats}

@Singleton
class TagService {
  def findOne(id: Long): JsValue = {
    Tag find(id) match {
      case Some(one) => Json.toJson(one)
      case None => Json.toJson(Result(false,"No entity"))
    }
  }

  def findAll: JsValue =  Json.toJson(Tag.findAll)

  def save(tag: Tag): Int = Tag.save(tag)

}
