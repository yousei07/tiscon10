package com.tiscon10.code;

/**
 * 治療歴有無を表すEnum。
 * 
 * @author TIS Taro
 */
public enum TreatedType implements CodeEnum {

    /** 病歴あり */
    TREATED(1, "はい"),
    /** 病歴なし */
    UNTREATED(0, "いいえ");

    /** 病歴有無のラベル */
    private final String label;
    /** 病歴有無のコード */
    private final int code;

    /**
     * コンストラクタ。
     *
     * @param code  コード値
     * @param label ラベル
     */
    TreatedType(int code, String label) {
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
    public static TreatedType getEnumFromCode(String code) {
        for (TreatedType treatedType : TreatedType.values()) {
            if (treatedType.hasCode(code)) {
                return treatedType;
            }
        }
        return null;
    }
}
