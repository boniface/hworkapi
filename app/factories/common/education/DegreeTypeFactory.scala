package factories.common.education

import domain.common.education.DegreeType

/**
  * Created by SONY on 2016-09-28.
  */
class DegreeTypeFactory {
  def createDegreeType(values:Map[String,String]):DegreeType={

    DegreeType(degreeTypeId = values("degreeTypeId"),name = values("name"))
  }

}
