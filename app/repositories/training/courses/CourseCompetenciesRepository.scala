package repositories.Training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.courses.CourseCompetencies

/**
 * Created by gavin.ackerman on 2016-11-08.
 */
class CourseCompetenciesRepository extends CassandraTable[CourseCompetenciesRepository, CourseCompetencies] {

  object compentencyId extends StringColumn(this) with PrimaryKey[String]

  object name extends StringColumn(this)

  object organisationId extends StringColumn(this) with PartitionKey[String]


  override def fromRow(r: Row): CourseCompetencies = {
    CourseCompetencies(
      compentencyId (r),
      name(r),
      organisationId(r)
    )
  }
}

object CourseCompetenciesRepository extends CourseCompetenciesRepository with RootConnector {
  override lazy val tableName = "coursecompetency"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(course: CourseCompetencies): Future[ResultSet] = {
    insert
      .value(_.compentencyId, course.compentencyId)
      .value(_.name, course.name)
      .value(_.organisationId, course.organisationId)
      .future()
  }

  def getAllCourseCourseCompetencies: Future[Seq[CourseCompetencies]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getCourseCompetenciesById(id: String): Future[Option[CourseCompetencies]] = {
    select.where(_.compentencyId eqs id).one()
  }

}
