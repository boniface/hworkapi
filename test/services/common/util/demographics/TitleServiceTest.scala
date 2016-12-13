package services.common.util.demographics

import domain.common.demographics.Title
import org.scalatest.FunSuite
import services.common.demographics.TitleService

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Aphiwe on 2016/12/13.
  */
class TitleServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val title = Title(
      "TitleID",
      "Mr")

    val result = Await.result(TitleService.apply.createOrUpdate(title), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetTitle") {
    val result = Await.result(TitleService.apply.getTitleById("TitleID"), 2.minutes)
    assert( result.head.name === "Mr")
  }
}
