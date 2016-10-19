package factories.training.courses

import domain.training.courses.Subject

/**
  * Created by SONY on 2016-10-18.
  */
class SubjectFactory
{
  def createSubject(values: Map[String, String],  credit: Int): Subject=
  {
    Subject(subjectId = values ("subjectId"), topic = values("topic"), subjectCode = values("subjectCode"),
      description = values("description"), credit = credit)
  }

}
