package factories.training.courses

import domain.training.courses.Subject

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
object SubjectFactory {
  def createSubject(values:Map[String, String], cred: Int):Subject={
    Subject(subjectId = values("subjectId"), topic = values("topic"),subjectCode = values("subjectCode"), description = values("description"), credit = cred)
  }
}
