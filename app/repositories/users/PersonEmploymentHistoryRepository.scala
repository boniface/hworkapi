package repositories.users
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.PersonEmploymentHistory

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class PersonEmploymentHistoryRepository extends CassandraTable[PersonEmploymentHistoryRepository,PersonEmploymentHistory]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this)
  object personEmploymentHistoryId extends StringColumn(this)
  object companyName extends StringColumn(this)
  object companyAddress extends StringColumn(this)
  object companyTelephone extends StringColumn(this)
  object applicantSupervisor extends StringColumn(this)
  object contactSupervisor extends StringColumn(this)
  object reasonsForLeaving extends StringColumn(this)
  object startDate extends DateTimeColumn(this)
  object endDate extends DateTimeColumn(this)
  object startingSalary extends BigDecimalColumn(this)
  object endingSalary extends BigDecimalColumn(this)
  object currencyId extends StringColumn(this)
  object jobResponsibility extends StringColumn(this)
  object jobId extends StringColumn(this)
  object date extends DateTimeColumn(this)
  object state extends StringColumn(this)

  override def fromRow(r: Row): PersonEmploymentHistory = {
    PersonEmploymentHistory(organisationId(r), userId(r), personEmploymentHistoryId(r), companyName(r),
      companyAddress(r), companyTelephone(r), applicantSupervisor(r), contactSupervisor(r), reasonsForLeaving(r),
      startDate(r), endDate(r), startingSalary(r), endingSalary(r), currencyId(r), jobResponsibility(r), jobId(r),
      date(r), state(r))
  }
}

object PersonEmploymentHistoryRepository extends PersonEmploymentHistoryRepository with RootConnector {
  override lazy val tableName = "personemploymenthistory"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personEmploymentHistory: PersonEmploymentHistory): Future[ResultSet] = {
    insert
      .value(_.organisationId, personEmploymentHistory.organisationId)
      .value(_.userId, personEmploymentHistory.userId)
      .value(_.personEmploymentHistoryId, personEmploymentHistory.personEmploymentHistoryId)
      .value(_.companyName, personEmploymentHistory.companyName)
      .value(_.companyAddress, personEmploymentHistory.companyAddress)
      .value(_.companyTelephone, personEmploymentHistory.companyTelephone)
      .value(_.applicantSupervisor, personEmploymentHistory.applicantSupervisor)
      .value(_.contactSupervisor, personEmploymentHistory.contactSupervisor)
      .value(_.reasonsForLeaving, personEmploymentHistory.reasonsForLeaving)
      .value(_.startDate, personEmploymentHistory.startDate)
      .value(_.endDate, personEmploymentHistory.endDate)
      .value(_.startingSalary, personEmploymentHistory.startingSalary)
      .value(_.endingSalary, personEmploymentHistory.endingSalary)
      .value(_.currencyId, personEmploymentHistory.currencyId)
      .value(_.jobResponsibility, personEmploymentHistory.jobResponsibility)
      .value(_.jobId, personEmploymentHistory.jobId)
      .value(_.date, personEmploymentHistory.date)
      .value(_.state, personEmploymentHistory.state)

      .future()
  }

  def findById(organisationId: String):Future[Option[PersonEmploymentHistory]] = {
    select.where(_.organisationId eqs organisationId).one()
  }
  def findAll: Future[Seq[PersonEmploymentHistory]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
