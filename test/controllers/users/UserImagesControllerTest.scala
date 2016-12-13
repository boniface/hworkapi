package controllers.users

import com.google.gson.Gson
import domain.users.UserImages
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by LuxoloM on 2016/12/13.
  */
class UserImagesControllerTest extends PlaySpec with OneAppPerTest {
  val gson = new Gson()
  val userImages = UserImages(
    "SIT52",
    "458K",
    "DO67",
    "https://zi.com",
    "Triu",
    "GHJ",
    Option("gugu"),
    new DateTime("2004-12-13T21:39:45.618-08:00"))

  "Routes" should {
    "UserImagesController" should {

      "Create UserImages Object in Through Controller" in {
        val request = route(app, FakeRequest(POST, "/userImages/create")
          .withJsonBody(Json.toJson(userImages)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get UserImages From Controller" in {
        val request = route(app, FakeRequest(GET, "/userImages/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }
}

