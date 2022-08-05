package org.ehrbase.serialisation.matrix;

import java.util.LinkedHashMap;
import java.util.Map;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
public class WalkerDto {

    private final Map<Resolve, Map<Index, Map<AqlPath, Object>>> matrix;

    private Resolve currentResolve;
    private Index currentIndex;

    public WalkerDto() {

        this.matrix = new LinkedHashMap<>();
    }

    public WalkerDto(WalkerDto other) {
        this.currentResolve = new Resolve(other.currentResolve);
        this.currentIndex = new Index(other.currentIndex);
        this.matrix = other.matrix;
    }

    public Map<Resolve, Map<Index, Map<AqlPath, Object>>> getMatrix() {
        return matrix;
    }

    public Resolve getCurrentResolve() {
        return currentResolve;
    }

    public void updateResolve(Resolve currentResolve) {
        this.currentResolve = currentResolve;
        matrix.put(currentResolve, new LinkedHashMap<>());
        Index key = new Index();
        matrix.get(currentResolve).put(key,new LinkedHashMap<>());
        currentIndex = key;

    }

    public Index getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Index currentIndex) {
        this.currentIndex = currentIndex;
    }
}
