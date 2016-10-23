package factories.training.courses

import domain.training.courses.Subject
import org.scalatest.FunSuite
/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class SubjectFactoryTest extends FunSuite{
  test("testCreateSubject")
  {
    val values = Map("subjectId"->"1000", "topic"->"Accounting","subjectCode"->"1000", "description"->"1000")
    val cred:Int = 20
    val subject = SubjectFactory.createSubject(values,cred)

    assert(subject == Subject(subjectId="1000", topic="Accounting",subjectCode="1000",description="1000", credit = 20))
  }
}
