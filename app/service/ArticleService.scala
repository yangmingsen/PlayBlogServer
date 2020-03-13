package service

import javax.inject.Singleton
import models.{Article, Result}
import play.api.libs.json.{JsValue, Json}
import myimpli.MyImplicit.{articleFormats, resultFormats}

@Singleton
class ArticleService {
  def findOne(id: Long): JsValue = {
    Article findOne(id) match {
      case Some(one) => Json.toJson(one)
      case None => Json.toJson(Result(false,"No entity"))
    }
  }

  private val FINALIST="select id,author_id,article_category_id,tag_id,title,update_time,browse_num,like_num,abstract_content from article"
  private val UPDATE_SQL = "UPDATE article SET"

  def findAll: JsValue = Json.toJson(Article.findAll(FINALIST))

  def findAllByTagId(params: Any*): JsValue = Json.toJson(Article.findAll(FINALIST+" where id IN (SELECT article_id FROM t_l_a WHERE tag_id=?)",params: _*))

  def findAllByCategoryId(params: Any*): JsValue = Json.toJson(Article.findAll(FINALIST+" where article_category_id=?",params: _*))

  def updateLikeNum(params: Any*) = Article.update(UPDATE_SQL+" like_num=like_num+1 WHERE id=?", params: _*)

}
