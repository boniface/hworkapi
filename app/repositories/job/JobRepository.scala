package repositories.job

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.job.Job

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/09.
 */

class JobRepository extends CassandraTable[JobRepository, Job] {

  object organisationId extends StringColumn(this) with PartitionKey[String]

  object jobId extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object jobClassificationId extends StringColumn(this)

  object title extends StringColumn(this)

  object code extends StringColumn(this)

  object description extends StringColumn(this)

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Descending

  object state extends StringColumn(this)


  override def fromRow(r: Row): Job = {
    Job(
      organisationId(r),
      jobId(r),
      jobClassificationId(r),
      title(r),
      code(r),
      description(r),
      date(r),
      state(r)
    )
  }
}

object JobRepository extends JobRepository with RootConnector {
  override lazy val tableName = "jobs"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(job: Job): Future[ResultSet] = {
    insert
      .value(_.organisationId, job.organisationId)
      .value(_.jobId, job.jobId)
      .value(_.jobClassificationId, job.jobClassificationId)
      .value(_.title, job.title)
      .value(_.code, job.code)
      .value(_.description, job.description)
      .value(_.date, job.date)
      .value(_.state, job.state)
      .future()
  }

  def getJobById(organisationId: String, jobId: String): Future[Option[Job]] = {
    select.where(_.organisationId eqs organisationId).and(_.jobId eqs jobId).one()
  }

  def getCompanyJobs(organisationId: String): Future[Seq[Job]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

}
