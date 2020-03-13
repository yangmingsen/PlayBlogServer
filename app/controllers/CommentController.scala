package controllers

import javax.inject._
import models.Comment
import myutils.DateHelper
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import service.CommentService

@Singleton
class CommentController @Inject()(val controllerComponents: ControllerComponents,
                                  commentService: CommentService) extends BaseController {

  /**
   * example: articleId=1561914320208921
   * @return
   */
  def findAllByArticleId() = Action { implicit request =>
    Form(single("articleId" -> longNumber)).bindFromRequest().fold(
      error => {
        Ok("articleId bind error")
      },
      articleId => {
        Ok(commentService.findAllArticleId(articleId))
      }
    )
  }

  def save() = Action { implicit request =>
    Form(mapping("id" -> optional(longNumber),
    "articleId" -> longNumber,
    "username" -> text,
    "content" -> optional(text),
    "createdTime"-> default(text, DateHelper.get()))(Comment.apply)(Comment.unapply)).bindFromRequest().fold(
      error => {
        Ok("comment bind error")
      },
      cmt => {
        Ok(commentService.save(cmt).toString)
      })
  }


}
