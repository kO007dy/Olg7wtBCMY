// 代码生成时间: 2025-09-05 04:59:32
package com.mathcalculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathCalculatorService {

    /// <summary>
    /// 计算两个数的加法
    /// </summary>
    /// <param name="a">加数1</param>
    /// <param name="b">加数2</param>
    /// <returns>两数之和</returns>
    @GetMapping("/add")
    public double add(@RequestParam double a, @RequestParam double b) {
        try {
            return a + b;
        } catch (Exception e) {
            // 错误处理
            throw new IllegalArgumentException("Invalid input for add operation");
        }
    }

    /// <summary>
    /// 计算两个数的减法
    /// </summary>
    /// <param name="a">被减数</param>
    /// <param name="b">减数</param>
    /// <returns>两数之差</returns>
    @GetMapping("/subtract")
    public double subtract(@RequestParam double a, @RequestParam double b) {
        try {
            return a - b;
        } catch (Exception e) {
            // 错误处理
            throw new IllegalArgumentException("Invalid input for subtract operation");
        }
    }

    /// <summary>
    /// 计算两个数的乘法
    /// </summary>
    /// <param name="a">乘数1</param>
    /// <param name="b">乘数2</param>
    /// <returns>两数之积</returns>
    @GetMapping("/multiply")
    public double multiply(@RequestParam double a, @RequestParam double b) {
        try {
            return a * b;
        } catch (Exception e) {
            // 错误处理
            throw new IllegalArgumentException("Invalid input for multiply operation");
        }
    }

    /// <summary>
    /// 计算两个数的除法
    /// </summary>
    /// <param name="a">被除数</param>
    /// <param name="b">除数</param>
    /// <returns>两数之商</returns>
    @GetMapping("/divide")
    public double divide(@RequestParam double a, @RequestParam double b) {
        try {
            if (b == 0) {
                // 错误处理
                throw new IllegalArgumentException("Division by zero is not allowed");
            }
            return a / b;
        } catch (Exception e) {
            // 错误处理
            throw new IllegalArgumentException("Invalid input for divide operation");
        }
    }
}
