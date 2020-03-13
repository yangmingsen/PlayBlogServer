package controllers

import javax.inject._
import models._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import service.{ArticleService, CategoryService, TagService}

import scala.collection.immutable.HashMap

@Singleton
class ArticleController @Inject()(val controllerComponents: ControllerComponents,
                                  articleService: ArticleService,
                                  tagService: TagService,
                                  categoryService: CategoryService) extends BaseController {

  def findOne(id: Long) = Action { implicit request =>
    Ok(articleService.findOne(id))
  }

  def findAll() = Action { implicit request =>
    Ok(articleService.findAll)
  }

  def index() = Action {implicit request =>
    val map = Map("categorys" -> categoryService.findAll,
      "tags" -> tagService.findAll,
      "articles" -> articleService.findAll,
      "nickName" -> Json.toJson("yms"))
    Ok(Json.toJson(map))
  }

  /***
   * example: tagId=1561914017459047
   * @return
   */
  def findAllByTagId = Action { implicit request =>
    Form(single("tagId" -> longNumber)).bindFromRequest().fold(
      error => {
        Ok("tagId form bind error")
      },
      single => {
        println("tagId="+single)
        Ok(articleService.findAllByTagId(single))
      }
    )
  }

  /***
   * example: categoryId=1
   * @return
   */
  def findAllByCategoryId = Action { implicit request =>
    Form(single("categoryId" -> number)).bindFromRequest().fold(
      error => {
        Ok("categoryId form bind error")
      },
      single => {
        Ok(articleService.findAllByCategoryId(single))
      }
    )
  }

}
