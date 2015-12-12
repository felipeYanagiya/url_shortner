package com.url.dto

import spray.json.DefaultJsonProtocol

/**
 * DTO Representing url object
 *
 * @author felipey.
 */
case class Url(id: String, longUrl: String, shortUrl: String)

object UrlProtocol extends DefaultJsonProtocol {
    implicit val formatUrl =  jsonFormat3(Url.apply)
}
