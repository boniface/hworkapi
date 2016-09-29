package factories.common.demographics

import domain.common.demographics.MaritalStatus

class MaritalStatusFactory {
  def createMaritalStatus(values:Map[String, String]):MaritalStatus={
 MaritalStatus(maritalStatusId = values("MaritalStatusId"),name = values("name"))
  }

}