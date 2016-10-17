package factories.job.position


import domain.position.PositionPackage
import factories.position.PositionPackageFactory
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by Yusiry on 10/17/2016.
  */
class PositionPackageTest extends FunSuite {

  test("testCreatePositionFunding") {
    val des = new PositionPackageFactory;

    val date = new DateTime(2016, 4, 2, 1, 20, 2, 0);

    val values = Map("positionId" -> "1",
      "positionPackageId" -> "1",
      "gradeId" -> "1",
      "notchId" -> "1",
      "state" -> "testState"
    );

    val posdes = des.createPositionPackageFactory(values, date);

    assert(posdes == PositionPackage(positionId = "1",
      positionPackageId = "1",
      gradeId = "1",
      notchId = "1",
      state = "testState",
      date = new DateTime(2016, 4, 2, 1, 20, 2, 0)));
  }
}
