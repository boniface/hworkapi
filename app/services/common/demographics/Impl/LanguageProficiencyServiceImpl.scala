package services.common.demographics.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.demographics.{LanguageProficiency, Title}
import repositories.common.demographics.LanguageProficiencyRepository
import services.Service
import services.common.demographics.LanguageProficiencyService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
class LanguageProficiencyServiceImpl extends LanguageProficiencyService with Service{
  override def createOrUpdate(languageProficiency: LanguageProficiency): Future[ResultSet] = {
    LanguageProficiencyRepository.save(languageProficiency)
  }

  override def getLanguageProficiencyById(id: String): Future[Option[Title]] = {
    LanguageProficiencyRepository.getLanguageProficiencyById(id)
  }

  override def getLanguageProficiencies(id: String): Future[Seq[Title]] = {
    LanguageProficiencyRepository.getLanguageProficiencies(id)
  }
}
