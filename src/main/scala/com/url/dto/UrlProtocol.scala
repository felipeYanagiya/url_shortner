package com.url.dto

import spray.json.DefaultJsonProtocol

/**
 * Formatter for Url response json
 *
 * @author felipey.
 */
object UrlProtocol extends DefaultJsonProtocol {
    implicit val format = jsonFormat4(UrlJson.apply)
}

case class UrlJson(longUrl : String, shortUrl: String, id: String, status: String)

