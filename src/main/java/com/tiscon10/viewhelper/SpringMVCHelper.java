package com.tiscon10.viewhelper;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.IOException;
import java.util.List;

/**
 * Spring MVCでHandlebars.javaを使用するためのヘルパークラス。
 */
public class SpringMVCHelper {

    /**
     * BindingResultをHandlebarsで扱うためのキー。
     * Controllerで、ModelにBindingResultを設定する際に使用する。
     *
     */
    public static final String BINDING_RESULT_KEY = "result";

    /**
     * {@link BindingResult#hasFieldErrors(String)}をHandlebarsで扱うためのHelper。
     * 引数で指定したフィールドにエラーがあるかどうかをチェックする。
     *
     * 指定した項目にエラーがある場合は、ブロック内の処理が実行される。
     *
     * 使用例
     * <code><pre>
     * {{#hasFieldErrors "kanjiName"}}
     *    <ul>
     *      {{#fieldErrors "kanjiName"}}<li style="color: red;">{{this}}</li>{{/fieldErrors}}
     *    </ul>
     * {{/hasFieldErrors}}
     * </pre></code>
     *
     */
    public static class HasFieldErrorsHelper implements Helper<String> {

        /**
         * @param field   エラー項目のフィールド名
         * @param options Handlebarsのオプション
         * @return 適用した結果（HTML）
         */
        @Override
        public Object apply(String field, Options options) throws IOException {
            BindingResult result = (BindingResult) options.context.get(BINDING_RESULT_KEY);
            if (result != null && result.hasFieldErrors(field)) {
                return options.fn();
            }
            return options.inverse();
        }
    }


    /**
     * {@link BindingResult#getFieldErrors(String)}をHandlebarsで扱うためのHelper。
     * 引数で指定したフィールドのエラー情報を出力する。
     *
     * 指定した項目にエラーがある場合は、ブロック内の処理が実行される。
     * エラーメッセージは、{@code this}で出力できる。
     * エラーが複数ある場合は、ループで1つずつ出力される。
     *
     * 使用例
     * <code><pre>
     *
     * {{#fieldErrors "kanjiName"}}
     *    <span style="color: red;">{{this}}</span>
     * {{/fieldErrors}}
     *
     * </pre></code>
     *
     * エラーが2件ある場合は以下のようにレンダリングされる。
     *
     * <code><pre>
     *
     * <span style="color: red;">10文字以内で入力してください</span>
     * <span style="color: red;">カタカナで入力してください</span>
     *
     * </pre></code>
     *
     */
    public static class FieldErrorsHelper implements Helper<String> {

        /**
         * @param field   エラー項目のフィールド名
         * @param options Handlebarsのオプション
         * @return 適用した結果（HTML）
         */
        @Override
        public Object apply(String field, Options options) throws IOException {
            BindingResult result = (BindingResult) options.context.get(BINDING_RESULT_KEY);
            if (result == null || !result.hasFieldErrors(field)) {
                // エラーがなければなにもしない
                return options.inverse();
            }
            // 項目に紐づくエラーを取得
            List<FieldError> fieldErrors = result.getFieldErrors(field);
            StringBuilder errorMessages = new StringBuilder();
            for (FieldError fieldError : fieldErrors) {
                // 1エラーずつ評価する
                String errorMessage = fieldError.getDefaultMessage();
                CharSequence applied = options.fn(errorMessage);
                errorMessages.append(applied);
            }
            return errorMessages.toString();
        }

    }
}
