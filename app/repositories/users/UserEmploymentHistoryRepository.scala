package repositories.users
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.UserEmploymentHistory

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class UserEmploymentHistoryRepository extends CassandraTable[UserEmploymentHistoryRepository,UserEmploymentHistory]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this) with PrimaryKey[String]
  object personEmploymentHistoryId extends StringColumn(this) with PrimaryKey[String]
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

  override def fromRow(r: Row): UserEmploymentHistory = {
    UserEmploymentHistory(organisationId(r), userId(r), personEmploymentHistoryId(r), companyName(r),
      companyAddress(r), companyTelephone(r), applicantSupervisor(r), contactSupervisor(r), reasonsForLeaving(r),
      startDate(r), endDate(r), startingSalary(r), endingSalary(r), currencyId(r), jobResponsibility(r), jobId(r),
      date(r), state(r))
  }
}

object UserEmploymentHistoryRepository extends UserEmploymentHistoryRepository with RootConnector {
  override lazy val tableName = "personemploymenthistory"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personEmploymentHistory: UserEmploymentHistory): Future[ResultSet] = {
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

  def findById(organisationId: String, userId: String, personEmploymentHistoryId: String):Future[Option[UserEmploymentHistory]] = {
    select.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.personEmploymentHistoryId eqs personEmploymentHistoryId).one()
  }
  def findAll: Future[Seq[UserEmploymentHistory]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getPersonEmploymentHistory(organisationId: String): Future[Seq[UserEmploymentHistory]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, userId: String, personEmploymentHistoryId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.personEmploymentHistoryId eqs personEmploymentHistoryId).future()
  }
}
