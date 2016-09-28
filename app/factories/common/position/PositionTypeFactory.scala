package factories.common.contacts

import domain.common.position.PositionType

class PositionTypeFactory {
  def createPositionType(values:Map[String, String]):positionType={
 PositionType(positionTypeId = values("positionTypeId"),name = values("name"))
  }

}