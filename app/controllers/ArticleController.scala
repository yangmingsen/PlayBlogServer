package controllers

import javax.inject._
import models._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
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

}
