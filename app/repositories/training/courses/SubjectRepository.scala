package repositories.training.courses
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.courses.Subject

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class SubjectRepository extends CassandraTable[SubjectRepository,Subject]{
  object subjectId extends StringColumn(this) with PartitionKey[String]
  object topic extends StringColumn(this)
  object subjectCode extends StringColumn(this)
  object description extends StringColumn(this)
  object credit extends IntColumn(this)


  override def fromRow(r: Row): Subject = {
    Subject(subjectId(r), subjectCode(r),subjectCode(r), description(r),credit(r))
  }
}

object SubjectRepository extends SubjectRepository with RootConnector {
  override lazy val tableName = "subject"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(subject: Subject): Future[ResultSet] = {
    insert
      .value(_.subjectId, subject.subjectId)
      .value(_.topic, subject.topic)
      .value(_.subjectCode, subject.subjectCode)
      .value(_.description, subject.description)
      .value(_.credit, subject.credit)
      .future()
  }

  def findById(subjectId: String):Future[Option[Subject]] = {
    select.where(_.subjectId eqs subjectId).one()
  }
  def findAll: Future[Seq[Subject]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(subjectId:String): Future[ResultSet] = {
    delete.where(_.subjectId eqs subjectId).future()
  }
}
