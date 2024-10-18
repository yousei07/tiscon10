package com.tiscon10.validator;

import com.tiscon10.code.CodeEnum;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * CodeValueの検証を行う実装クラス。
 * 
 * @author TIS Taro
 */
public class CodeValueValidator implements ConstraintValidator<CodeValue, String> {

    /** コードとラベルを持つEnumのClass */
    private Class<? extends CodeEnum> enumClass;

    /**
     * CodeValueValidator を初期化する。
     *
     * @param constraintAnnotation 対象プロパティに付与されたアノテーション
     */
    @Override
    public void initialize(CodeValue constraintAnnotation) {
        enumClass = constraintAnnotation.value();
    }

    /**
     * 検証対象の値が指定したenumクラスに含まれるかどうかを検証する。
     *
     * @param value   検証対象の値
     * @param context バリデーションコンテキスト
     * @return 含まれる場合 {@code true}
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return CodeEnum.matches(enumClass, value);
    }
}
