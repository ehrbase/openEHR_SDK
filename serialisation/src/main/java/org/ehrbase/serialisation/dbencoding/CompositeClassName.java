package org.ehrbase.serialisation.dbencoding;

import com.nedap.archie.rm.datavalues.DataValue;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;

public class CompositeClassName {

    private DataValue dataValue;

    public CompositeClassName(DataValue dataValue) {
        this.dataValue = dataValue;
    }

    /**
     * extrapolate composite class name such as DvInterval<DvCount>
     *
     * @return
     */
    public String toString(){
        String classname = new SimpleClassName(dataValue).toString();

        if ("DvInterval".equals(classname) && !(((DvInterval) dataValue).getLower() == null && ((DvInterval) dataValue).getUpper() == null)) {//get the classname of lower/upper
            DvInterval interval = (DvInterval) dataValue;
            String lowerClassName = null, upperClassName = null;

            //either lower or upper or both are set value
            if (interval.getLower() != null)
                lowerClassName = new SimpleClassName(interval.getLower()).toString();

            if (interval.getUpper() != null)
                upperClassName = new SimpleClassName(interval.getUpper()).toString();

            if (lowerClassName != null && upperClassName != null && (!lowerClassName.equals(upperClassName)))
                throw new IllegalArgumentException("Lower and Upper classnames do not match:" + lowerClassName + " vs." + upperClassName);

            return classname + "<" + (lowerClassName != null ? lowerClassName : upperClassName) + ">";
        }
        return classname;
    }
}
