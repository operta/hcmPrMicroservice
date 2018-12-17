package ba.infostudio.com.service;

import ba.infostudio.com.service.dto.PrPayrollSettingsDTO;

public class UserPayrollComposition {
    private String userId;

    private PrPayrollSettingsDTO payrollSettings;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PrPayrollSettingsDTO getPayrollSettings() {
        return payrollSettings;
    }

    public void setPayrollSettings(PrPayrollSettingsDTO payrollSettings) {
        this.payrollSettings = payrollSettings;
    }

    @Override
    public String toString() {
        return "UserPayrollComposition{" +
            "userId=" + userId +
            ", payrollSettingsDTO=" + payrollSettings +
            '}';
    }
}
