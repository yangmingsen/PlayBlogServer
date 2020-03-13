package controllers

;

import javax.inject._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import service.{ArticleService, CategoryService, CommentService, TagService}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class InfoController @Inject()(val controllerComponents: ControllerComponents,
                               articleService: ArticleService,
                               commentService: CommentService,
                               categoryService: CategoryService,
                               tagService: TagService) extends BaseController {

  def findOne() = Action { implicit request =>
    Form(single("id" -> longNumber)).bindFromRequest().fold(
      error => {
        Ok("id bind error")
      },
      articleId => {
        val article = articleService.findOne(articleId)
        val comments = commentService.findAllArticleId(articleId)
        val tags = tagService.findAll
        val categorys = categoryService.findAll

        val map = Map("categorys" -> categorys, "tags" -> tags,
          "article" -> article, "comments" -> comments)

        Ok(Json.toJson(map))
      }
    )
  }

  def updateLikeNum = Action { implicit request =>
    Form(single("id" -> longNumber)).bindFromRequest().fold(
      error => {
        Ok("id bind error")
      },
      id => {
        Ok(articleService.updateLikeNum(id).toString)
      }
    )
  }

}
