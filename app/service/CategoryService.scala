package service

import javax.inject.Singleton
import models.Category
import play.api.libs.json.{JsValue, Json}
import myimpli.MyImplicit.{categoryWrites, resultWrites}

@Singleton
class CategoryService {

  def findAll = Json.toJson(Category.findAll)

}
