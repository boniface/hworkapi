package factories.storage

import domain.storage.FileMeta
import org.scalatest.FunSuite


/**
  * Created by SONY on 2016-09-28.
  */

class FileMetaFactoryTest extends FunSuite{

  test("testCreateFileMeta"){
    val fileM = new FileMetaFactory;

    val values = Map("organisationId" -> "1",
      "fileName" -> "FileM",
      "contentType" -> "KJ1"
    );

    val fMeta = fileM.createFileMeta(values);

    assert(fMeta == FileMeta(organisationId = "1",
      fileName = "FileM",
      contentType = "KJ1"
      ));
  }
}

