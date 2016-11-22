package repositories.users
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.PersonDemographics

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class PersonDemographicsRepository extends CassandraTable[PersonDemographicsRepository,PersonDemographics]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this) with PrimaryKey[String]
  object personDemographicsId extends StringColumn(this) with PrimaryKey[String]
  object genderId extends StringColumn(this)
  object dateOfBirth extends DateTimeColumn(this)
  object maritalStatusId extends StringColumn(this)
  object numberOfDependencies extends IntColumn(this)
  object date extends DateTimeColumn(this)
  object state extends StringColumn(this)

  override def fromRow(r: Row): PersonDemographics = {
    PersonDemographics(organisationId(r), userId(r), personDemographicsId(r), genderId(r),
      dateOfBirth(r), maritalStatusId(r), numberOfDependencies(r), date(r), state(r))
  }
}

object PersonDemographicsRepository extends PersonDemographicsRepository with RootConnector {
  override lazy val tableName = "persondemographics"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personDemographics: PersonDemographics): Future[ResultSet] = {
    insert
      .value(_.organisationId, personDemographics.organisationId)
      .value(_.userId, personDemographics.userId)
      .value(_.personDemographicsId, personDemographics.personDemographicsId)
      .value(_.genderId, personDemographics.genderId)
      .value(_.dateOfBirth, personDemographics.dateOfBirth)
      .value(_.maritalStatusId, personDemographics.maritalStatusId)
      .value(_.numberOfDependencies, personDemographics.numberOfDependencies)
      .value(_.date, personDemographics.date)
      .value(_.state, personDemographics.state)

      .future()
  }

  def findById(organisationId: String, userId: String, personDemographicsId: String):Future[Option[PersonDemographics]] = {
    select.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.personDemographicsId eqs personDemographicsId).one()
  }
  def findAll: Future[Seq[PersonDemographics]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getPersonDemographics(organisationId: String): Future[Seq[PersonDemographics]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, userId: String, personDemographicsId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.userId eqs userId). and (_.personDemographicsId eqs personDemographicsId).future()
  }
}
