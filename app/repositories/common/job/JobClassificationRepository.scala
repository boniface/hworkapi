package repositories.common.job

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.job.JobClassification

import scala.concurrent.Future

//id: String,
//currentTitle: String,
//oldTitle: String,
//oldCode: String,
//currentCode: String,
//codeConversion: String,
//comment: String

sealed class JobClassificationRepository extends CassandraTable[JobClassificationRepository, JobClassification] {

  object id extends StringColumn(this) with PartitionKey[String]

  object currentTitle extends StringColumn(this)

  object oldTitle extends StringColumn(this)

  object oldCode extends StringColumn(this)

  object currentCode extends StringColumn(this)

  object codeConversion extends StringColumn(this)

  object comment extends StringColumn(this)

  object state extends StringColumn(this)

  override def fromRow(r: Row): JobClassification = {
    JobClassification(
      id(r),
      currentTitle(r),
      oldTitle(r),
      oldCode(r),
      currentCode(r),
      codeConversion(r),
      comment(r),
      state(r))
  }
}

object JobClassificationRepository extends JobClassificationRepository with RootConnector {
  override lazy val tableName = "jobcl"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(jobClassification: JobClassification): Future[ResultSet] = {
    insert
      .value(_.id, jobClassification.id)
      .value(_.comment, jobClassification.comment)
      .value(_.currentTitle, jobClassification.currentTitle)
      .value(_.codeConversion, jobClassification.codeConversion)
      .value(_.oldTitle, jobClassification.oldTitle)
      .value(_.oldCode, jobClassification.oldCode)
      .value(_.currentCode, jobClassification.currentCode)
      .value(_.state, jobClassification.state)
      .future()
  }

  def findById(id: String): Future[Option[JobClassification]] = {
    select.where(_.id eqs id).one()
  }

  def findAll: Future[Seq[JobClassification]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id: String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
