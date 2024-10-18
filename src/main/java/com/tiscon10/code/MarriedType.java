package com.tiscon10.code;

/**
 * 配属者有無を表すEnum。
 * 
 * @author TIS Taro
 */
public enum MarriedType implements CodeEnum {

    /** 配偶者あり */
    MARRIED(1, "配偶者あり"),
    /** 配偶者なし */
    UNMARRIED(0, "配偶者なし");

    /** 配偶者有無のラベル */
    private final String label;
    /** 配偶者有無のコード */
    private final int code;

    /**
     * コンストラクタ。
     *
     * @param code  コード値
     * @param label ラベル
     */
    MarriedType(int code, String label) {
        this.label = label;
        this.code = code;
    }

    /**
     * ラベルを返却する。
     *
     * @return ラベル
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * コード値を返却する。
     *
     * @return コード値
     */
    @Override
    public int getCode() {
        return code;
    }

    /**
     * コード値からenumを返却する。
     * 存在しないコード値の場合はnullを返却する。
     * 
     * @param code コード値
     * @return enum
     */
    public static MarriedType getEnumFromCode(String code) {
        for (MarriedType marriedType : MarriedType.values()) {
            if (marriedType.hasCode(code)) {
                return marriedType;
            }
        }
        return null;
    }
}
