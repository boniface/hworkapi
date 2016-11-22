package services.common.demographics.Impl

import com.datastax.driver.core.ResultSet
import domain.common.demographics.Gender
import repositories.common.demographics.GenderRepository
import services.Service
import services.common.demographics.GenderService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
class GenderServiceImpl extends GenderService with Service{

  override def createOrUpdate(gender: Gender): Future[ResultSet] = {
    GenderRepository.save(gender)
  }

  override def getGenderById(id: String): Future[Option[Gender]] = {
    GenderRepository.getGenderById(id)
  }

  override def getGenders(id: String): Future[Seq[Gender]] = {
    GenderRepository.getGenders(id)
  }
}
