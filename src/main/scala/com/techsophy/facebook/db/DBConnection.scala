package com.techsophy.facebook.db

import slick.jdbc.JdbcProfile

trait DBConnection {

  val driver: JdbcProfile

  def db: driver.backend.DatabaseDef

}
