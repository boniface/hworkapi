package repositories.users
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.UserLanguage

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-21.
  */
class UserLanguageRepository extends CassandraTable[UserLanguageRepository,UserLanguage]{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this) with PrimaryKey[String]
  object personLanguageId extends StringColumn(this) with PrimaryKey[String]
  object languageId extends StringColumn(this)
  object reading extends StringColumn(this)
  object writing extends StringColumn(this)
  object speaking extends StringColumn(this)
  object date extends DateTimeColumn(this)
  object state extends StringColumn(this)

  override def fromRow(r: Row): UserLanguage = {
    UserLanguage(organisationId(r), userId(r), personLanguageId(r), languageId(r), reading(r), writing(r), speaking(r), date(r), state(r))
  }
}

object UserLanguageRepository extends UserLanguageRepository with RootConnector {
  override lazy val tableName = "personlanguage"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personLanguage: UserLanguage): Future[ResultSet] = {
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

  def findById(organisationId: String, userId: String, personLanguageId:String):Future[Option[UserLanguage]] = {
    select.where(_.organisationId eqs organisationId). and(_.userId eqs userId).and(_.personLanguageId eqs personLanguageId).one()
  }
  def findAll: Future[Seq[UserLanguage]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getPersonLanguage(organisationId: String): Future[Seq[UserLanguage]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId:String, userId: String, personLanguageId:String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId). and(_.userId eqs userId).and(_.personLanguageId eqs personLanguageId).future()
  }
}
