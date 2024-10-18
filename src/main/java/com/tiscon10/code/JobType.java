package com.tiscon10.code;

/**
 * 職業を表すEnum。
 * 
 * @author TIS Taro
 */
public enum JobType implements CodeEnum {

    /** 経営自営 */
    MANAGER(1, "経営・自営業"),
    /** 会社員 */
    EMPLOYEE(2, "会社員"),
    /** 契約派遣 */
    TEMPORARY(3, "契約・派遣社員"),
    /** 公務員 */
    PUBLICWORKER(4, "公務員"),
    /** 民間団体 */
    NGO(5, "民間団体"),
    /** 主婦 */
    HOUSEWIFE(6, "主婦"),
    /** 学生 */
    STUDENT(7, "学生"),
    /** 年金受給 */
    PENSIONER(8, "年金受給者の方"),
    /** パートアルバイト */
    PERTTIMEWORKER(9, "パート・アルバイト"),
    /** 他有職 */
    EMPLOYED(10, "その他 (有職）"),
    /** 他無職 */
    UNEMPLOYED(11, "その他 (無職）");

    /** 職業のラベル */
    private final String label;
    /** 職業のコード */
    private final int code;

    /**
     * コンストラクタ。
     *
     * @param code  コード値
     * @param label ラベル
     */
    JobType(int code, String label) {
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
    public static JobType getEnumFromCode(String code) {
        for (JobType jobType : JobType.values()) {
            if (jobType.hasCode(code)) {
                return jobType;
            }
        }
        return null;
    }
}
