package com.url.service

import scalaj.http.Base64

/**
 * Service to short an url address
 *
 * @author felipey.
 */
class ShortUrlService {

    def validateUrl(longUrl: String): Unit = {
        if (longUrl == null) {
            throw new IllegalArgumentException
        }
        //TODO validate input url format

    }

    def shortUrlAddress(longUrl: String): String = {
        validateUrl(longUrl)

        val urls = longUrl.split('.')
        val firstHalf = urls(1).substring(0)
        val secondHalf = urls(2).substring(0, 1)

        urls(0) + firstHalf + "." + secondHalf + "/" + Base64.encodeString(longUrl)
    }

}
