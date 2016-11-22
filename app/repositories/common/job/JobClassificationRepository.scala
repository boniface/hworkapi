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

  object jobClassificationId extends StringColumn(this) with PartitionKey[String]

  object currentTitle extends StringColumn(this)

  object oldTitle extends StringColumn(this)

  object oldCode extends StringColumn(this)

  object currentCode extends StringColumn(this)

  object codeConversion extends StringColumn(this)

  object comment extends StringColumn(this)

  override def fromRow(r: Row): JobClassification = {
    JobClassification(
      jobClassificationId(r),
      currentTitle(r),
      oldTitle(r),
      oldCode(r),
      currentCode(r),
      codeConversion(r),
      comment(r))
  }
}

object JobClassificationRepository extends JobClassificationRepository with RootConnector {
  override lazy val tableName = "jobcl"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(jobClassification: JobClassification): Future[ResultSet] = {
    insert
      .value(_.jobClassificationId, jobClassification.jobClassificationId)
      .value(_.comment, jobClassification.comment)
      .value(_.currentTitle, jobClassification.currentTitle)
      .value(_.codeConversion, jobClassification.codeConversion)
      .value(_.oldTitle, jobClassification.oldTitle)
      .value(_.oldCode, jobClassification.oldCode)
      .value(_.currentCode, jobClassification.currentCode)
      .future()
  }

  def getJobClassificationById(jobClassificationId: String): Future[Option[JobClassification]] = {
    select.where(_.jobClassificationId eqs jobClassificationId).one()
  }

  def getJobClassifications(jobClassificationId:String): Future[Seq[JobClassification]] = {
    select.where(_.jobClassificationId eqs jobClassificationId).fetchEnumerator() run Iteratee.collect()
  }
  def getJobClassification(jobClassificationId: String): Future[Seq[JobClassification]] = {
    select.where(_.jobClassificationId eqs jobClassificationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(jobClassificationId: String): Future[ResultSet] = {
    delete.where(_.jobClassificationId eqs jobClassificationId).future()
  }
}
