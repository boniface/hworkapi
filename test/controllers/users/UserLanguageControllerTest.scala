package controllers.users

import com.google.gson.Gson
import domain.users.UserLanguage
import org.joda.time.DateTime
import org.scalatest.FunSuite
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by LuxoloM on 2016/12/13.
  */
class UserLanguageControllerTest extends PlaySpec with OneAppPerTest {
  val gson = new Gson()
  val userImages = UserLanguage(
    "SIT52",
    "458K",
    "DO67",
    "LA24",
    "Yes",
    "Yes",
    "Yes",
    new DateTime("2004-12-13T21:39:45.618-08:00"),
    "gugu")

  "Routes" should {
    "UserLanguageController" should {

      "Create UserLanguage Object in Through Controller" in {
        val request = route(app, FakeRequest(POST, "/userLanguage/create")
          .withJsonBody(Json.toJson(userImages)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get UserLanguage From Controller" in {
        val request = route(app, FakeRequest(GET, "/userLanguage/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }
}
