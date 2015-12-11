package com.url.dto

import spray.json.DefaultJsonProtocol

/**
 * Formatter for input Post json
 *
 * @author felipey.
 */

object LongUrlProtocol extends DefaultJsonProtocol {
    implicit val format = jsonFormat1(LongUrl.apply)
}

case class LongUrl(url: String) {

}



