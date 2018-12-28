package ir.app_service.model.entity.enumeration;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public enum CommissionType {

    SALE(0.03d), RENT(0.25d);

    private double commission;

    CommissionType(double commission) {
        this.commission = commission;
    }

    public double getCommission() {
        return commission;
    }
}
