package models

import scalikejdbc._

case class Category(id: Int, categoryName: String, createdTime: String) extends AnyEntity

object Category {
  def findAll(implicit session: DBSession = AutoSession) = {
    DB readOnly { dbs =>
      dbs.list("select * from category") { rs =>
        Category(rs.int("id"), rs.string("category_name"), rs.string("created_time"))
      }
    }
  }
}
