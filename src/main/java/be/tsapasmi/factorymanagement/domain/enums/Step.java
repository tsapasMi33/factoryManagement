package be.tsapasmi.factorymanagement.domain.enums;

import lombok.Getter;

@Getter
public enum Step {
    ENCODED(false,false),
    PRODUCTION(false, false),
    CUT(true,true),
    BENT(true, true),
    COMBINED(true,true),
    WELDED(true, true),
    ASSEMBLED(true, true),
    FINISHED(false, true),
    PACKED(false, false),
    SENT(false, false);

    private final boolean batchStep;
    private final boolean measurable;

    Step(boolean batchStep, boolean measurable) {
        this.batchStep = batchStep;
        this.measurable = measurable;
    }

}
