package factories.position

import domain.position.PositionDesignation
import org.joda.time.DateTime

/**
  * Created by theo on 2016/10/06.
  */
class PositionDesignationFactory {
  def createPositionDesignation(values:Map[String, String], date:DateTime):PositionDesignation={
    PositionDesignation(positionId = values("positionId"),
      positionDesignationId = values("positionDesignationId"),
      date = date,
      designationId = values("designationId"),
      state = values("state"))

  }

}
