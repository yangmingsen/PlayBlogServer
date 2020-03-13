package models

import scalikejdbc._

case class Article(id: Option[Long], authorId: Int, articleCategoryId: Int, tagId: Option[String], title: String, markdownDoc: Option[String],
                   content: Option[String], createdTime: Option[String], updateTime: Option[String], browseNum: Int, likeNum: Int, isPublic: Int,
                   publishStates: Int, isDel: Int, keywords: Option[String], abstractContent: String, allowComment: Int) extends AnyEntity {

  def this(id: Long, authorId: Int, articleCategoryId: Int, tagId: String, title: String, updateTime: String, browseNum: Int, likeNum: Int,
           abstractContent: String) = this(Some(id),authorId,articleCategoryId,Some(tagId),title,None,None,None,Option(updateTime),
              browseNum,likeNum,0,0,0,None,abstractContent,1)
}

object Article {
  def findOne(id: Long)(implicit session: DBSession = AutoSession): Option[Article] ={
    DB readOnly{ dbs =>
      dbs.single("select * from article where id=?",id) { rs =>
        Article(Some(rs.long("id")), rs.int("author_id"),rs.int("article_category_id"),
          Option(rs.string("tag_id")), rs.string("title"), Option(rs.string("markdown_doc")),
          Option(rs.string("content")), Option(rs.string("created_time")), Option(rs.string("update_time")),
          rs.int("browse_num"), rs.int("like_num"), rs.int("is_public"), rs.int("publish_states"),
          rs.int("is_del"), Option(rs.string("keywords")), rs.string("abstract_content"), rs.int("allow_comment"))
      }
    }
  }

  def findAll(template: String, params: Any*)(implicit session: DBSession = AutoSession): List[Article] = {
    DB readOnly { dbs =>
      dbs.list(template, params: _*) { rs =>
        new Article(rs.long("id"), rs.int("author_id"), rs.int("article_category_id"), rs.string("tag_id"),
          rs.string("title"),rs.string("update_time"), rs.int("browse_num"), rs.int("like_num"),
          rs.string("abstract_content"))
      }
    }
  }

  def update(template: String, params: Any*)(implicit session: DBSession = AutoSession) = {
    DB localTx { dbs =>
      dbs.update(template,params: _*)
    }
  }

}
