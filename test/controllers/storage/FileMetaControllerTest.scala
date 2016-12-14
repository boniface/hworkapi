package controllers.storage

import com.google.gson.Gson
import domain.storage.FileMeta
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by LuxoloM on 2016/12/13.
  */
class FileMetaControllerTest extends PlaySpec with OneAppPerTest {
  val gson = new Gson()
  val fileMeta = FileMeta(
    "SIT52",
    "Mozulla",
    "Triupa")

  "Routes" should {
    "FileMetaController" should {

      "Create FileMeta Object in Through Controller" in {
        val request = route(app, FakeRequest(POST, "/fileMeta/create")
          .withJsonBody(Json.toJson(fileMeta)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get FileMeta From Controller" in {
        val request = route(app, FakeRequest(GET, "/fileMeta/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }
}
