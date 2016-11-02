package services.common.demographics


import com.datastax.driver.core.ResultSet
import domain.common.demographics.Gender
import services.common.demographics.Impl.GenderServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
trait GenderService {

  def createOrUpdate(gender: Gender):Future[ResultSet]

  def getGenderById(gender:String):Future[Option[Gender]]

  def getGenders(gender:String):Future[Seq[Gender]]

}

object GenderService{

  def apply: GenderService = new GenderServiceImpl()
}
