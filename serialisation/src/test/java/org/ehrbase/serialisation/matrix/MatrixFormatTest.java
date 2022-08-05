package org.ehrbase.serialisation.matrix;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.jupiter.api.Test;

/**
 * @author Stefan Spiska
 */
class MatrixFormatTest {

  @Test
  void toMatrix() throws IOException {

      String corona = IOUtils.toString(CompositionTestDataCanonicalJson.MULTI_OCCURRENCE.getStream(), StandardCharsets.UTF_8);

      MatrixFormat cut = new MatrixFormat(new TestDataTemplateProvider());

      Map<Resolve, Map<Index, Map<AqlPath, Object>>> actual = cut.toMatrix(new CanonicalJson().unmarshal(corona));

        System.out.println(actual);
  }
}