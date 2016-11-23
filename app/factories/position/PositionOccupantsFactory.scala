package factories.position

import domain.position.PositionOccupants
import org.joda.time.DateTime

/**
  * Created by theo on 2016/10/06.
  */
class PositionOccupantsFactory {
  def createPositionOccupants(values:Map[String, String], date:DateTime):PositionOccupants={
    PositionOccupants(positionId = values("positionId"),
      positionOccupantId = values("positionOccupantId"),
      date = date,
      userId = values("userId"),
      state = values("state"))
  }

}
