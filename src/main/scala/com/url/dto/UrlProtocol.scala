package com.url.dto

import spray.json.DefaultJsonProtocol

/**
 * @author felipey.
 */
object UrlProtocol {

}

case class Url(longUrl : String, shortUrl: String, id: String)

object Url extends DefaultJsonProtocol {
    implicit val format = jsonFormat3(Url.apply)
}
