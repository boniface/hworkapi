package factories.training.competencies

import domain.training.competencies.Competency

/**
  * Created by SONY on 2016-10-18.
  */
class CompetencyFactory
{
  def createCompetency(values: Map[String, String]): Competency=
  {
    Competency(compencyId = values("compencyId"), name = values("name"), competencyTypeId = values("competencyTypeId"))
  }

}
