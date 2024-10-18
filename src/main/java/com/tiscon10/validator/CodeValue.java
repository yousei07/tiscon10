package com.tiscon10.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.tiscon10.code.CodeEnum;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * {@link CodeEnum}のコード値のバリデーションを行うクラス。
 * 
 * @author TIS Taro
 */
@Documented
@Constraint(validatedBy = CodeValueValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface CodeValue {

    /**
     * バリデーションエラー発生時に設定するメッセージ。
     *
     * @return メッセージ
     */
    String message() default "{tiscon.CodeValue.message}";

    /**
     * コードenumを取得する。
     *
     * @return コードenum
     */
    Class<? extends CodeEnum> value();

    /** 複数指定用のアノテーション */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        /**
         * CodeValueの配列を取得する。
         *
         * @return CodeValueの配列
         */
        CodeValue[] value();
    }

    /**
     * グループを取得する。
     *
     * @return グループ
     */
    Class<?>[] groups() default {};

    /**
     * Payloadを取得する。
     *
     * @return Payload
     */
    Class<? extends Payload>[] payload() default {};
}
