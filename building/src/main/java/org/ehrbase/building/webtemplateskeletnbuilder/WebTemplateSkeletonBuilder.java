package org.ehrbase.building.webtemplateskeletnbuilder;

import com.nedap.archie.aom.CComplexObject;
import com.nedap.archie.creation.RMObjectCreator;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Archetyped;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.TemplateId;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.ArchetypeID;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.FlatPath;

import java.util.Collections;
import java.util.List;

import static org.ehrbase.util.rmconstants.RmConstants.RM_VERSION_1_4_0;

public class WebTemplateSkeletonBuilder {

    private static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    private static final RMObjectCreator RM_OBJECT_CREATOR =
            new RMObjectCreator(ARCHIE_RM_INFO_LOOKUP);



    public static Composition build(WebTemplate template, boolean withChildren){

        Composition composition = build(template.getTree(), withChildren, Composition.class);

        composition.setArchetypeDetails(new Archetyped());
        composition.getArchetypeDetails().setTemplateId(new TemplateId());
        composition.getArchetypeDetails().getTemplateId().setValue(template.getTemplateId());
        composition.getArchetypeDetails().setRmVersion(RM_VERSION_1_4_0);
    composition
        .getArchetypeDetails()
        .setArchetypeId(new ArchetypeID(composition.getArchetypeNodeId()));

        return composition;
    }


    @SuppressWarnings("unchecked")
    public  static <T>  T build(WebTemplateNode node, boolean withChildren, Class<T>  clazz){

        String rmclass = node.getRmType();

        CComplexObject elementConstraint = new CComplexObject();
        elementConstraint.setRmTypeName(rmclass);
        Object skeleton;

        switch (rmclass) {
            case "UID_BASED_ID":
                skeleton = new HierObjectId();
                break;
            case "PARTY_PROXY":
                skeleton = new PartyIdentified();
                break;
            case "STRING":
            case "LONG":
                skeleton = null;
                break;
            case "BOOLEAN":
                skeleton = false;
                break;
            default:
                skeleton = RM_OBJECT_CREATOR.create(elementConstraint);
                break;
        }

        if (withChildren){
            node.getChildren().stream()
                    .filter(n -> !List.of("name","archetype_node_id","offset").contains(n.getId()))
                    .forEach(c ->{
                        Object childObject = build(c, true, Object.class);

                        insert(node, (RMObject) skeleton,c ,childObject);
                    });
        }

        if (skeleton instanceof Locatable){
            ((Locatable) skeleton).setName(new DvText(node.getName()));
            ((Locatable) skeleton).setArchetypeNodeId(node.getNodeId());
        }

        if (skeleton == null || clazz.isAssignableFrom(skeleton.getClass())) {
          return (T) skeleton;
            } else {
                throw new SdkException(String.format("%s not assignable from %s", skeleton.getClass(), clazz));
        }
    }

    public static void insert(WebTemplateNode parentNode, RMObject parentObject, WebTemplateNode childNode, Object childObject){

        String attributeName = FlatPath.removeStart(new FlatPath(childNode.getAqlPath(true)), new FlatPath(parentNode.getAqlPath(true))).getLast().getName();

        RM_OBJECT_CREATOR.addElementToListOrSetSingleValues(
                parentObject, attributeName, Collections.singletonList(childObject));
    }
}
