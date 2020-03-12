package models
import play.api.libs.json._
import scalikejdbc._

case class Tag(id: Long, tagName: Option[String], createdTime: String) extends AnyEntity

object Tag {
  def find(id: Long)(implicit session: DBSession = AutoSession): Option[Tag] = {
    DB readOnly{ session =>
      session.single("select * from tag where id=?",id) { rs =>
        Tag(rs.long("id"),Option(rs.string("tag_name")),rs.string("created_time"))
      }
    }
  }

  def findAll(implicit session: DBSession = AutoSession): List[Tag] = {
    DB readOnly { session =>
      session.list("select * from tag") { rs =>
        Tag(rs.long("id"),Option(rs.string("tag_name")),rs.string("created_time"))
      }
    }
  }

  def save(tag: Tag)(implicit session: DBSession = AutoSession): Int = {
    DB localTx { session =>
      session.update("insert into (id,tag_name,created_time)tag(?,?,?)",tag.id,tag.tagName,tag.createdTime)
    }
  }

}