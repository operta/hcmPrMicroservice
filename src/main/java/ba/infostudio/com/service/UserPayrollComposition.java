package ba.infostudio.com.service;

import ba.infostudio.com.service.dto.PrPayrollSettingsDTO;

public class UserPayrollComposition {
    private Long userId;

    private PrPayrollSettingsDTO payrollSettings;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
