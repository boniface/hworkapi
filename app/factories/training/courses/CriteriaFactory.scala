package factories.training.courses

import domain.training.courses.Criteria

/**
  * Created by SONY on 2016-10-18.
  */
class CriteriaFactory
{
  def createCriteria(values: Map[String, String]): Criteria=
  {
    Criteria(criteriaId = values("criteriaId"), name = values("name"))
  }
}
