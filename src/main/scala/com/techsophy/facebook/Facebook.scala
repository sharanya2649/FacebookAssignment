package com.techsophy.facebook

import java.sql.Date
import java.time

import com.techsophy.facebook.db.DBConnection
import facebook4j.{Post, ResponseList}

trait Facebook extends{
  val facebookQuery: FacebookQuery

  def insert_feeds(feeds: ResponseList[Post]) = {
    println(feeds)
    feeds.forEach { i => facebookQuery.insert(List(FacebookPost(i.getId, i.getMessage,new java.sql.Date(i.getCreatedTime.getTime))) )}
  }
}

object FacebookObj extends Facebook {
  override val facebookQuery: FacebookQuery = FacebookQueryObj
}

