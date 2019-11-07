package com.techsophy.facebook



import java.sql.Date

import com.techsophy.facebook.db.{DBConnection, MySQLDBConnector}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


trait FacebookQuery extends FacebookDetails {

  import driver.api._

//  def insert(facebookPost: FacebookPost): Future[Int] = {
//    db.run(fb += facebookPost)
//  }

  def insert(facebookPost: List[FacebookPost]): Future[Option[Int]] = {
    db.run(fb ++= facebookPost)
  }

//  def insert(id: String, userPost: String) = {
//    db.run(fb.map(t => (t.id, t.post)) += (id, userPost))
//  }
}

trait FacebookDetails extends DBConnection {

  import driver.api._
//  implicit def dateImplicit = MappedColumnType.base[java.util.Date, java.sql.Timestamp](
//    { utilDate => new Timestamp(utilDate.getTime) },
//    { timestamp => new Date(timestamp.getTime) }
//  )

  val fb = TableQuery[FacebookTable]

  def create =
    Await.result(db.run(DBIO.seq(
      fb.schema.create
    )), Duration.Inf)

  class FacebookTable(tag: Tag) extends Table[FacebookPost](tag, "facebook_page_posts") {
    def * = (id, post,time) <> (FacebookPost.tupled, FacebookPost.unapply)

    def id = column[String]("id")

    def post = column[String]("post")

    def time=column[Date]("created_time")
  }

}

case class FacebookPost(id: String, post: String,time:Date)

object FacebookQueryObj extends FacebookQuery with MySQLDBConnector