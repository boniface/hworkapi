package factories.common.demographics

import domain.common.demographics.IdentificationType

class IdentificationTypeFactory {
  def createIdentificationType(values:Map[String, String]):IdentificationType={
 IdentificationType(identificationTypeId = values("identificationTypeId"),
                    name = values("name"),
                    description =values("description"))
  }

}