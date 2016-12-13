package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserLanguage
import services.users.UserLanguageService
import services.Service
import repositories.users.UserLanguageRepository

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class UserLanguageServiceImpl extends UserLanguageService with Service{
  override def createOrupdate(personLanguage: UserLanguage): Future[ResultSet] = {
    UserLanguageRepository.save(personLanguage)
  }

  override def getPersonLanguageById(organisationId: String, userId: String, personLanguageid: String): Future[Option[UserLanguage]] = {
    UserLanguageRepository.findById(organisationId,userId,personLanguageid)
  }

  override def getAllPersonLanguages(person: String): Future[Seq[UserLanguage]] = {
    UserLanguageRepository.findAll
  }
}
