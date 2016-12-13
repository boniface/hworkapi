package services.common.demographics.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.demographics.{LanguageProficiency}
import repositories.common.demographics.LanguageProficiencyRepository
import services.Service
import services.common.demographics.LanguageProficiencyService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
class LanguageProficiencyServiceImpl extends LanguageProficiencyService with Service{
  def createOrUpdate(languageProficiency: LanguageProficiency): Future[ResultSet] = {
    LanguageProficiencyRepository.save(languageProficiency)
  }

  def getLanguageProficiencyById(id: String): Future[Option[LanguageProficiency]] = {
    LanguageProficiencyRepository.getLanguageProficiencyById(id)
  }

  def getLanguageProficiencies(id: String): Future[Seq[LanguageProficiency]] = {
    LanguageProficiencyRepository.getLanguageProficiencies(id)
  }
}
