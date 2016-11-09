package repositories.users
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.PersonLanguage

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class PersonLanguageRepository extends CassandraTable[PersonLanguageRepository,PersonLanguage]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this) with PrimaryKey[String]
  object personLanguageId extends StringColumn(this) with PrimaryKey[String]
  object languageId extends StringColumn(this)
  object reading extends StringColumn(this)
  object writing extends StringColumn(this)
  object speaking extends StringColumn(this)
  object date extends DateTimeColumn(this)
  object state extends StringColumn(this)

  override def fromRow(r: Row): PersonLanguage = {
    PersonLanguage(organisationId(r), userId(r), personLanguageId(r), languageId(r), reading(r), writing(r), speaking(r), date(r), state(r))
  }
}

object PersonLanguageRepository extends PersonLanguageRepository with RootConnector {
  override lazy val tableName = "personlanguage"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personLanguage: PersonLanguage): Future[ResultSet] = {
    insert
      .value(_.organisationId, personLanguage.organisationId)
      .value(_.userId, personLanguage.userId)
      .value(_.personLanguageId, personLanguage.personLanguageId)
      .value(_.languageId, personLanguage.languageId)
      .value(_.reading, personLanguage.reading)
      .value(_.writing, personLanguage.writing)
      .value(_.speaking, personLanguage.speaking)
      .value(_.date, personLanguage.date)
        .value(_.state, personLanguage.state)
      .future()
  }

  def findById(organisationId: String, userId: String, personLanguageId:String):Future[Option[PersonLanguage]] = {
    select.where(_.organisationId eqs organisationId). and(_.userId eqs userId).and(_.personLanguageId eqs personLanguageId).one()
  }
  def findAll: Future[Seq[PersonLanguage]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getPersonLanguage(organisationId: String): Future[Seq[PersonLanguage]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, userId: String, personLanguageId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.userId eqs userId).and(_.personLanguageId eqs personLanguageId).future()
  }
}
