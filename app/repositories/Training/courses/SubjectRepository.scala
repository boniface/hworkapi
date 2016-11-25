package repositories.Training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future
import conf.connection.DataConnection
import domain.training.courses.Subject

/**
 * Created by gavin.ackerman on 2016-11-09.
 */
class SubjectRepository extends CassandraTable[SubjectRepository, Subject] {


  object topic extends StringColumn(this)

  object subjectId extends StringColumn(this) with PrimaryKey[String]

  object subjectCode extends StringColumn(this)

  object description extends StringColumn(this)

  object credit extends IntColumn(this)





  override def fromRow(r: Row): Subject = {
    Subject(
      subjectId(r),
      topic(r),
      subjectCode(r),
      description(r),
      credit(r)

    )
  }
}

object SubjectRepository extends SubjectRepository with RootConnector {
  override lazy val tableName = "subjects"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session



  def save(sub: Subject): Future[ResultSet] = {
    insert
      .value(_.subjectId, sub.subjectId)
      .value(_.topic, sub.topic)
      .value(_.subjectCode, sub.subjectCode)
      .value(_.description, sub.description)
      .value(_.credit, sub.credit)

      .future()
  }
  def getAllSubject: Future[Seq[Subject]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getSubjectById(id: String): Future[Option[Subject]] = {
    select.where(_.subjectId eqs id).one()
  }



}
