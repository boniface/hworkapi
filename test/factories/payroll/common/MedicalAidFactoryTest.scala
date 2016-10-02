package factories.payroll.common
import domain.payroll.common.MedicalAid
import org.scalatest.FunSuite

/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
class git MedicalAidFactoryTest extends FunSuite {
  test("testMedicalAid")
  {
    val values = Map("organisationId"->"1000", "medicalAidId"->"MA2000", "name"->"Annual", "code"->"code")
    val medicalAid = MedicalAidFactory.createMedicalAid(values)
    assert(medicalAid == MedicalAid(organisationId="1000", medicalAidId = "MA2000", name = "Annual", code = "code"))
  }

}
