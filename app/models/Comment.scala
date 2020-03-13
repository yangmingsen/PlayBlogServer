package models

import scalikejdbc._

case class Comment(id: Option[Long], articleId: Long, username: String, content: Option[String], createdTime: String) extends AnyEntity

object Comment{
  def save(template: String,params: Any*)(implicit session: DBSession = AutoSession) = {
    DB localTx { dbs =>
      dbs.update(template, params: _*)
    }
  }

  def findAll(template: String,params: Any*)(implicit session: DBSession = AutoSession) = {
    DB readOnly { dbs =>
      SQL(template).bind(params: _*).map{ rs =>
        Comment(Option(rs.long("id")), rs.long("article_id"), rs.string("username"),
          Option(rs.string("content")), rs.string("created_time"))
      }.list.apply()
    }
  }

}

