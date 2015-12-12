package com.url.service

import akka.actor.Actor
import com.datastax.driver.core.Session
import com.url.commons.utils.cassandra.Connector
import com.url.dao.UrlDao
import com.url.dto.{CompleteUrl, LongUrl, Url}
import spray.http._
import spray.routing._

import scala.concurrent.Await
import scala.concurrent.duration._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class UrlShortnerActor extends Actor with UrlShortnerService {

    // the HttpService trait defines only one abstract member, which
    // connects the services environment to the enclosing actor or test
    def actorRefFactory = context

    // this actor only runs our route, but you could add
    // other things here, like request stream processing
    // or timeout handling
    def receive = runRoute(myRoute)
}

// this trait defines our service behavior independently from the service actor
trait UrlShortnerService extends HttpService {

    import com.url.dto.CompleteUrlProtocol._
    import com.url.dto.LongUrlProtocol._
    import com.url.dto.UrlProtocol._
    import spray.httpx.SprayJsonSupport._

    val shortUrlService = new ShortUrlService
    val urlDao = new UrlDao {
        override def getSession(keySpace: String): Session = cluster.connect(Connector.keyspace.name)
    }

    val myRoute =
        get {
            path(Segment) { id =>
                respondWithMediaType(MediaTypes.`application/json`) {
                    complete {
                        val result = Await.result(urlDao.dao.getById(id), 5.seconds).get
                        CompleteUrl(result.longUrl, result.shortUrl, result.id, "OK")
                    }
                }
            }
        } ~
            post {
                path("") {
                    entity(as[LongUrl]) { inputUrl =>
                        respondWithMediaType(MediaTypes.`application/json`) {
                            complete {
                                val url = shortUrlService.getUrlJson(inputUrl.url)
                                val result = Url(url.id, url.longUrl, url.shortUrl)
                                urlDao.dao.saveOrUpdate(result)
                                result
                            }
                        }
                    }
                }

            }
}
