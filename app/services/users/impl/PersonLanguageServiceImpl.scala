package services.users.impl
import com.websudos.phantom.dsl.ResultSet
import domain.users.PersonLanguage
import services.users.PersonLanguageService
import services.Service
import repositories.users.PersonLanguageRepository

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class PersonLanguageServiceImpl extends PersonLanguageService with Service{
  override def createOrupdate(personLanguage: PersonLanguage): Future[ResultSet] = {
    PersonLanguageRepository.save(personLanguage)
  }

  override def getPersonLanguageById(organisationId: String, userId: String, personLanguageid: String): Future[Option[PersonLanguage]] = {
    PersonLanguageRepository.findById(organisationId,userId,personLanguageid)
  }

  override def getAllPersonLanguages(person: String): Future[Seq[PersonLanguage]] = {
    PersonLanguageRepository.findAll
  }
}
