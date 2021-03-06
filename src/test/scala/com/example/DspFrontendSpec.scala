package com.example

import org.scalatest.{ Matchers, FunSpec }
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest

class DspFrontendSpec extends FunSpec with Matchers with ScalatestRouteTest with BidResponseJsonFormats {
  describe("DspFrontend") {
    it("should return a proper bid response for bid_request") {
      pending

      Get("/bid_request?auction_id=12&ip=127.0.0.1&bundle_name=com.facebook&connection_type=WiFi") ~> DspFrontend() ~> check {
        status === StatusCodes.OK

        val response = responseAs[Bid]

        response.result shouldEqual "bid"
        response.auctionId shouldEqual "12"
        response.currency shouldEqual "USD"
        response.bid should (be  > 0.035 and be < 0.055)
      }
    }

    it ("should return a proper winner response for winner request") {
      Get("/winner/6c831376-c1df-43ef-a377-85d83aa3314c") ~> DspFrontend() ~> check {
        status === StatusCodes.OK
        responseAs[String] shouldEqual "OK"
      }
    }
  }
}
