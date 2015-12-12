package com.url.dto

import spray.json.DefaultJsonProtocol

/**
 * Formatter for Url response json
 *
 * @author felipey.
 */
case class CompleteUrl(longUrl : String, shortUrl: String, id: String, status: String)

object CompleteUrlProtocol extends DefaultJsonProtocol {
    implicit val format4 = jsonFormat4(CompleteUrl.apply)
}

