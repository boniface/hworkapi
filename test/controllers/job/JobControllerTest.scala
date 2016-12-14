package controllers.job

import com.google.gson.Gson
import domain.job.Job
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by LuxoloM on 2016/12/14.
  */
class JobControllerTest extends PlaySpec with OneAppPerTest {

  val gson = new Gson()
  val job = Job(
    "1",
    "1",
    "1",
    "SIT52",
    "7754",
    "Triu",
    new DateTime("2016-12-13T21:39:45.618-08:00"),
    "RSA"
  )

  "Routes" should {
    "JobController" should {

      "Create Job Object in Through Controller" in {
        val request = route(app, FakeRequest(POST, "/job/create")
          .withJsonBody(Json.toJson(job)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Job From Controller" in {
        val request = route(app, FakeRequest(GET, "/job/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }
}