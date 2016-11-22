package factories.training.competencies

import domain.training.competencies.CompetencyType

/**
  * Created by SONY on 2016-10-18.
  */
class CompetencyTypeFactory
{
  def createCompetencyType(values: Map[String, String]): CompetencyType = {
    CompetencyType(competencyTypeId = values("competencyTypeId"), name = values("name"))
  }
}
