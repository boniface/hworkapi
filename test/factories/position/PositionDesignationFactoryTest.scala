package factories.position

import domain.position.PositionDesignation
import org.scalatest.FunSuite
import org.joda.time.DateTime

import scala.collection.JavaConverters._

/**
  * Created by theo on 2016/10/10.
  */
class PositionDesignationFactoryTest extends FunSuite{

  test("testCreatePositionDesignation") {
    val des = new PositionDesignationFactory;

    val date = new DateTime(2016, 9, 11, 4, 20, 0, 0);

    val values = Map("positionId" -> "1",
      "positionDesignationId" -> "2",
      "designationId" -> "5",
      "state" -> "wisconsin");

    val posdes = des.createPositionDesignation(values, date);

    assert(posdes == PositionDesignation(positionId = "1",
      positionDesignationId = "2",
      date = new DateTime(2016, 9, 11, 4, 20, 0, 0),
      designationId = "5",
      state = "wisconsin"));

  }

}
