package org.ehrbase.serialisation.matrix;

import com.nedap.archie.rm.composition.Composition;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.webtemplate.templateprovider.TemplateProvider;

/**
 * @author Stefan Spiska
 */
public class MatrixFormat {

    private final TemplateProvider templateProvider;

    public MatrixFormat(TemplateProvider templateProvider) {
        this.templateProvider = templateProvider;
    }

    Map<Resolve, Map<Index, Map<AqlPath, Object>>> toMatrix(Composition composition) {
        String templateId = composition.getArchetypeDetails().getTemplateId().getValue();
        Resolve currentResolve = new Resolve();
        currentResolve.setPathFromRoot(AqlPath.ROOT_PATH);
        currentResolve.setArchetypeId(composition.getArchetypeNodeId());
        WalkerDto walkerDto = new WalkerDto();
        walkerDto.updateResolve(currentResolve);
        new CompositionToMatrixWalker()
                .walk(
                        composition,
                        walkerDto,
                        templateProvider.buildIntrospect(templateId).get().getTree(),
                        templateId);

        return walkerDto.getMatrix();
    }
}
