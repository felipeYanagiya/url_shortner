package com.url

import com.url.service.UrlShortnerService
import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._

class UrlShortnerServiceSpec extends Specification with Specs2RouteTest with UrlShortnerService {
  def actorRefFactory = system

  "MyService" should {

    "return url from service" in {
      Get() ~> myRoute ~> check {
        responseAs[String] must contain("Say hello")
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Post() ~> myRoute ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }
  }
}
