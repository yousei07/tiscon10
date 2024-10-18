package com.tiscon10.code;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * コード値を定義したEnumが実装するインタフェース。
 * 
 * @author TIS Taro
 */
public interface CodeEnum {
    /**
     * ラベルを返却する。
     *
     * @return ラベル
     */
    String getLabel();

    /**
     * コード値を返却する。
     *
     * @return コード値
     */
    int getCode();

    /**
     * 与えられたコード値が当該区分のコード値と等しいか判定する。
     * 
     * @param expression コードの文字列表現
     * @return 等価である場合、真
     */
    default boolean hasCode(String expression) {
        if (expression == null) {
            return false;   // nullの場合は等価ではありえない
        }
        try {
            return getCode() == Integer.parseInt(expression);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 与えられたコード値が当該区分のコード値と等しいか判定する。
     *
     * @param code コード値
     * @return 等しい場合、真
     */
    default boolean hasCode(int code) {
        return getCode() == code;
    }

    /**
     * 指定されたenumから、コード値に合致する要素を取得する。
     *
     * @param codeEnumClass enumクラス
     * @param value         コード値
     * @param <T>           enumの型
     * @return 合致したenum
     * @throws java.util.NoSuchElementException 合致する要素が見つからなかった場合
     */
    static <T extends CodeEnum> T find(Class<T> codeEnumClass, int value) {
        return enumStream(codeEnumClass)
            .filter(e -> e.hasCode(value))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException(
                "enum:[" + codeEnumClass + "], value=[" + value + "]"));
    }

    /**
     * 指定されたenumから、コード値に合致する要素を取得する。
     *
     * @param codeEnumClass    enumクラス
     * @param stringExpression コード値の文字列表現
     * @param <T>              enumの型
     * @return 合致したenum
     * @throws java.util.NoSuchElementException 合致する要素が見つからなかった場合
     */
    static <T extends CodeEnum> T find(Class<T> codeEnumClass, String stringExpression) {
        return enumStream((codeEnumClass))
            .filter(e -> e.hasCode(stringExpression))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException(
                "enum:[" + codeEnumClass + "], value=[" + stringExpression + "]"));
    }

    /**
     * 与えられた文字列表現と等価である要素が存在するか判定する。
     *
     * @param codeEnumClass    enumクラス
     * @param stringExpression 文字列表現
     * @param <T>              enumの型
     * @return 合致した要素が存在する場合、真
     */
    static <T extends CodeEnum> boolean matches(Class<T> codeEnumClass, String stringExpression) {
        return enumStream(codeEnumClass)
            .anyMatch(code -> code.hasCode(stringExpression));
    }

    /**
     * 指定された{@link Enum}クラスの{@link Stream}を取得する。
     *
     * @param codeEnumClass クラス
     * @param <T>           Enumの型
     * @return Stream
     */
    private static <T extends CodeEnum> Stream<T> enumStream(Class<T> codeEnumClass) {
        T[] enums = codeEnumClass.getEnumConstants();
        Objects.requireNonNull(enums, codeEnumClass + " must be enum.");
        return Arrays.stream(enums);
    }
}
