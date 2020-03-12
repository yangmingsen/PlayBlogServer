package controllers

import javax.inject._
import models.Tag
import myutils.{DateHelper, IdGenerator}
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import service.TagService

@Singleton
class TagController @Inject()(val controllerComponents: ControllerComponents,
                              tagService: TagService) extends BaseController {

  def findOne(id: Long): Action[AnyContent] = Action {implicit  request =>
    Ok(tagService.findOne(id))
  }



  def findAll() = Action { implicit request =>
    Ok(tagService.findAll)
  }


  def save() = Action { implicit request =>
    Form(mapping("id" -> default(longNumber, IdGenerator.getTagId),
      "tagName" -> optional(text),
      "creatdTime" -> default(nonEmptyText, DateHelper.get())
    )(Tag.apply)(Tag.unapply)).bindFromRequest().fold(
      formWithErrors => {
        Ok("tag form bind error")
      },
      tag => {
        println(tag.toString)
        Ok("save ok")
      }
    )
  }

}
