package org.ehrbase.openehr.sdk.examplegenerator;

import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class ExampleGeneratorConfigStaus {

    private static ArchieRMInfoLookup rmInfoLookup = ArchieRMInfoLookup.getInstance();

    private static RMTypeInfo getCategory(RMTypeInfo typeInfo) {

        RMTypeInfo dataValue = rmInfoLookup.getTypeInfo("DATA_VALUE");
        RMTypeInfo pathable = rmInfoLookup.getTypeInfo("PATHABLE");

        RMTypeInfo category;
        if (typeInfo.isDescendantOf(dataValue)) {
            return dataValue;
        }
        if (typeInfo.isDescendantOf(pathable)) {
            return pathable;
        }
        return Optional.of(typeInfo)
                .map(RMTypeInfo::getAllParentClasses)
                .map(a -> {
                    switch (a.size()) {
                        case 0:
                        case 1:
                            return typeInfo;
                        default:
                            return IterableUtils.get(a, a.size() - 2);

                    }
                })
                .get();
    }

    @Test
    void testHandledRmTypes() {
        Map<RMTypeInfo, List<RMTypeInfo>> byBaseType = ExampleGeneratorConfig.Handlers.getHandledRmTypes().stream()
                .map(rmInfoLookup::getTypeInfo)
                .collect(Collectors.groupingBy(t -> getCategory(t)));

        byBaseType.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().getRmName()))
                .forEach(e -> {
                    System.out.println(e.getKey().getRmName() + ":");

                    e.getValue().stream()
                            .sorted(Comparator.<RMTypeInfo, Boolean>comparing(v -> e.getKey().getRmName().equals(v.getRmName())).reversed().thenComparing(RMTypeInfo::getRmName))
                            .forEach(v -> System.out.println("\t" + v.getRmName()));

                });

    }

    @Test
    void testUnhandledRmTypes() {

        var handledTypes = ExampleGeneratorConfig.Handlers.getHandledRmTypes();

        List<RMTypeInfo> allTypes = rmInfoLookup.getAllTypes();

//        allTypes.removeIf(t -> handledTypes.contains(t.getRmName()));

        Map<RMTypeInfo, List<RMTypeInfo>> byBaseType = allTypes.stream()
                .collect(Collectors.groupingBy(t -> getCategory(t)));

        byBaseType.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().getRmName()))
                .forEach(e -> {
                    System.out.println(e.getKey().getRmName() + ":");

                    e.getValue().stream()
                            .sorted(Comparator.<RMTypeInfo, Boolean>comparing(v -> e.getKey().getRmName().equals(v.getRmName())).reversed().thenComparing(RMTypeInfo::getRmName))
                            .forEach(v -> {
                                System.out.print("\t");
                                if (handledTypes.contains(v.getRmName())) {
                                    System.out.print("✓ ");
                                }
                                System.out.println("\t" + v.getRmName());
                            });

                });

    }

}