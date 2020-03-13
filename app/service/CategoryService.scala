package service

import javax.inject.Singleton
import models.Category
import play.api.libs.json.{JsValue, Json}
import myimpli.MyImplicit.{categoryFormats, resultFormats}

@Singleton
class CategoryService {

  def findAll = Json.toJson(Category.findAll)

}
