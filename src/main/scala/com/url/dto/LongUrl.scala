package com.url.dto

import spray.json.DefaultJsonProtocol

/**
 * Formatter for input Post json
 *
 * @author felipey.
 */
case class LongUrl(url: String)

object LongUrlProtocol extends DefaultJsonProtocol {
    implicit val format = jsonFormat1(LongUrl.apply)
}



