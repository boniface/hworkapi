package factories.common.position

import domain.common.position.PositionType

/**
  * Created by SONY on 2016-09-28.
  */
class PositionTypeFactory {
  def createPositionType(values:Map[String,String]):PositionType={
    PositionType(positionTypeId = values("positionTypeId"),name = values("name"))
  }
}
