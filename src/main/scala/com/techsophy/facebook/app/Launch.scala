package com.techsophy.facebook.app

import com.techsophy.facebook.{FacebookObj, FacebookQueryObj}
import facebook4j.FacebookFactory
import facebook4j.auth.AccessToken

object Launch extends App {
  val facebook = new FacebookFactory().getInstance
  facebook.setOAuthAppId("", "")
  val accessTokenString = ""
  val appToken=""
  val at = new AccessToken(accessTokenString)
  val pt=new AccessToken(appToken)
  facebook.setOAuthAccessToken(at)
  facebook.setOAuthAccessToken(pt)
  FacebookQueryObj.create
  FacebookObj.insert_feeds(facebook.getFeed("me"))
}
