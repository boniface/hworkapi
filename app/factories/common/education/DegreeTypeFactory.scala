package factories.common.education

import domain.common.education.DegreeType

class DegreeTypeFactory {
  def createDegreeType(values:Map[String, String]):DegreeType={
 DegreeType(degreeTypeId = values("degreeTypeId"),name = values("name"))
  }

}