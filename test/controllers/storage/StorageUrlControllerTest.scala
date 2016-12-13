package controllers.storage

import com.google.gson.Gson
import domain.storage.StorageUrl
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by LuxoloM on 2016/12/13.
  */
class StorageUrlControllerTest extends PlaySpec with OneAppPerTest {
  val gson = new Gson()
  val storageUrl = StorageUrl(
    "SIT52",
    "458K",
    "Triu",
    "https://zi.com")

  "Routes" should {
    "StorageUrlController" should {

      "Create StorageUrl Object in Through Controller" in {
        val request = route(app, FakeRequest(POST, "/storageUrl/create")
          .withJsonBody(Json.toJson(storageUrl)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get StorageUrl From Controller" in {
        val request = route(app, FakeRequest(GET, "/storageUrl/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }
}
