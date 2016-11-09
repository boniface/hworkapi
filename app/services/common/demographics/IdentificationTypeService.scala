package services.common.demographics

import com.websudos.phantom.dsl._
import domain.common.demographics.IdentificationType
import services.common.demographics.Impl.IdentificationTypeServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
trait IdentificationTypeService {

  def createOrUpdate(identificationType:IdentificationType):Future[ResultSet]

  def getIdentificationTypeById(identification:String):Future[Option[IdentificationType]]

  def getIdentificationTypes(identification:String):Future[Seq[IdentificationType]]

}

object IdentificationTypeService{
  def apply: IdentificationTypeService = new IdentificationTypeServiceImpl()
}