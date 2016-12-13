package controllers.storage

import com.google.gson.Gson
import domain.storage.FileResults
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._


/**
  * Created by LuxoloM on 2016/12/13.
  */
class FileResultsControllerTest extends PlaySpec with OneAppPerTest {
  val gson = new Gson()
  val fileResults = FileResults(
    "SIT52",
    "MO47",
    "https://tri.com",
    Option("Triupa"))

  "Routes" should {
    "FileResultsController" should {

      "Create FileResults Object in Through Controller" in {
        val request = route(app, FakeRequest(POST, "/fileResults/create")
          .withJsonBody(Json.toJson(fileResults)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get FileResults From Controller" in {
        val request = route(app, FakeRequest(GET, "/fileResults/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }
}
