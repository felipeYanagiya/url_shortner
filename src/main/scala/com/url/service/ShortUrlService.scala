package com.url.service

import scalaj.http.Base64

/**
 * Service to short an url address
 *
 * @author felipey.
 */
class ShortUrlService {

    def shortUrlAddress(longUrl : String): String = {
        if (longUrl == null) {
          throw new IllegalArgumentException
        } else {
            Base64.encodeString(longUrl)
        }
    }

}
