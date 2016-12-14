package controllers.common.util

import com.google.gson.Gson
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._


/**
  * Created by hashcode on 2016/09/07.
  */

class MailControllerTest extends PlaySpec with OneAppPerTest {
  val mail = Mail(
    "SITEID",
    "no_reply_no@hashcode.zm",
    "test",
    "smtp.gmail.com",
    "587",
    "ACTIVE",
    new DateTime("2004-12-13T21:39:45.618-08:00"))

  "Routes" should {



    "MailController" should {

      "Create Mail Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/util/mail/create")
          .withJsonBody(Json.toJson(mail)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Mail From Controller" in {
        val request = route(app, FakeRequest(GET, "/util/mail/SITEID")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }
}
