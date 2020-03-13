package service

import javax.inject.Singleton
import play.api.libs.json.{JsValue, Json}
import myimpli.MyImplicit.{commentFormats, resultFormats}
import models.Comment

@Singleton
class CommentService {
  private val INSERT_SQL="INSERT INTO comment (article_id,username,content,created_time)VALUES(?,?,?,?)"
  private val SELECT_SQL="SELECT id,article_id,username,content,created_time FROM comment"

  def save(params: Any*) = Comment.save(INSERT_SQL, params: _*)

  def save(comment: Comment) = Comment.save(INSERT_SQL, comment.articleId,comment.username,comment.content,comment.createdTime)

  def findAllArticleId(params: Any*) = Json.toJson(Comment.findAll(SELECT_SQL+" WHERE article_id=? ORDER BY created_time DESC", params: _*))

}
