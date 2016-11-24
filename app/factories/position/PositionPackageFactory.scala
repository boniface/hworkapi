package factories.position

import domain.position.PositionPackage
import org.joda.time.DateTime

/**
  * Created by Yusiry on 10/17/2016.
  */
class PositionPackageFactory {
  def createPositionPackageFactory(values:Map[String, String], date:DateTime): PositionPackage = {
  PositionPackage(positionId = values("positionId"),
    positionPackageId = values("positionPackageId"),
    gradeId = values("gradeId"),
    notchId = values("notchId"),
    state = values("state"),
    date = date)
  }

}
