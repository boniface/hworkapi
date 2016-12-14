package services.common.util.demographics

import domain.common.demographics.Race
import org.scalatest.FunSuite
import services.common.demographics.RaceService

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by Aphiwe on 2016/12/13.
  */
class RaceServiceTest extends FunSuite{

  test("testSaveOrUpdate") {
    val race = Race(
      "RaceID",
      "African")

    val result = Await.result(RaceService.apply.createOrUpdate(race), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetRace") {
    val result = Await.result(RaceService.apply.getRaceById("RaceID"), 2.minutes)
    assert( result.head.name === "African")
  }
}
