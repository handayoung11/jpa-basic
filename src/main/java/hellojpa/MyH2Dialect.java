package hellojpa;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.AvgWithArgumentCastFunction;

public class MyH2Dialect extends H2Dialect {

    public MyH2Dialect() {
        this.registerFunction("average", new AvgWithArgumentCastFunction("double"));
    }
}
