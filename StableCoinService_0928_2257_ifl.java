// 代码生成时间: 2025-09-28 22:57:54
package com.example.stablecoin;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

// 稳定币服务类
@Service
public class StableCoinService {

    private final AtomicReference<BigDecimal> stableCoinValue;

    // 构造方法注入稳定币的初始值
    @Autowired
    public StableCoinService(StableCoinConfig config) {
        this.stableCoinValue = new AtomicReference<>(config.getInitialValue());
    }

    // 获取当前稳定币的价值
    public BigDecimal getCurrentValue() {
        return stableCoinValue.get();
    }

    // 设置稳定币的价值
    public void setCurrentValue(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value cannot be null or negative");
        }
        stableCoinValue.set(value);
    }

    // 更新稳定币的价值
    public void updateValue(BigDecimal adjustment) {
        if (adjustment == null || adjustment.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Adjustment cannot be null or negative");
        }
        stableCoinValue.updateAndGet(prevValue -> prevValue.add(adjustment));
    }

    // 稳定币配置类
    public static class StableCoinConfig {
        private BigDecimal initialValue;

        public BigDecimal getInitialValue() {
            return initialValue;
        }

        public void setInitialValue(BigDecimal initialValue) {
            this.initialValue = initialValue;
        }
    }
}
