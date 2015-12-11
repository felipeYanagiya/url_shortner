package com.url.service

import akka.actor.Actor
import com.url.dto.LongUrl
import spray.http._
import spray.routing._

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

    import com.url.dto.LongUrlProtocol._
    import spray.httpx.SprayJsonSupport._

    val myRoute =
        get {
            path("") {
                respondWithMediaType(MediaTypes.`application/json`) {
                    complete {
                        LongUrl("bla")
                    }
                }
            }
        } ~
       post {
           path("") {
               entity(as[LongUrl]) { longUrl =>
                   respondWithMediaType(MediaTypes.`application/json`) {
                       complete {
                           LongUrl(longUrl.url)
                       }
                   }
               }
           }

       }
}
