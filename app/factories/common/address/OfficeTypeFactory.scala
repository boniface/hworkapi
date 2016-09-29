package factories.common.address

import domain.common.address.OfficeType

class OfficeTypeFactory {
  def createOfficeType(values:Map[String, String]):OfficeType={
  OfficeType(officeTypeId = values("officeTypeId"),
              name = values("name"),
              code = values("code"),
              state = values("state"))
  }

}