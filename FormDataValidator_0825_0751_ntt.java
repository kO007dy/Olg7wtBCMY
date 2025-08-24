// 代码生成时间: 2025-08-25 07:51:19
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 表单数据验证器，用于验证表单数据是否符合预期格式
 */
@Component
public class FormDataValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        // 验证器仅支持特定的类
        return FormData.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FormData formData = (FormData) target;
        
        // 验证名称是否为空
        if (formData.getName() == null || formData.getName().trim().isEmpty()) {
            errors.rejectValue("name", "name.required", "Name is required");
        }

        // 验证邮箱是否符合格式
        if (formData.getEmail() == null || !formData.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.rejectValue("email", "email.invalid", "Invalid email format");
        }

        // 验证年龄是否在合理范围内
        if (formData.getAge() < 0 || formData.getAge() > 120) {
            errors.rejectValue("age", "age.outOfRange", "Age must be between 0 and 120");
        }
    }
}

/**
 * 表单数据类，用于封装表单数据
 */
public class FormData {
    private String name;
    private String email;
    private int age;

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}