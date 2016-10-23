package factories.training.competency

import java.util

import domain.training.competencies.Competency



/**
 * Created by gavin.ackerman on 2016-10-23.
 */
object CompetencyFactory {
  def createCompetency(values:util.Map[String, String]):Competency={
    Competency(compencyId = values("compencyId"), name = values("name"), competencyTypeId = values("competencyTypeId"))
  }
}
