package com.tiscon10.controller.sample;

import com.tiscon10.Tiscon10Application;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class SampleController {

    @GetMapping("/sample")
    public String sample(Model model) {

        // 単一の値を表示する例
        model.addAttribute("price", 1000);

        // クラスのプロパティを表示する例
        model.addAttribute("product", new Product("うまい棒", 20));

        // Mapの値を表示する例
        model.addAttribute("person", Map.of("name", "TIS太郎", "age", 30));

        // リストの値（クラス）を表示する例
        model.addAttribute("products", List.of(
            new Product("うまい棒", 20),
            new Product("ポテチ", 100),
            new Product("チョコレート", 200)
        ));

        // リストの値（単一）を表示する例
        model.addAttribute("scores", List.of(78, 94, 85));

        // 条件分岐の例
        model.addAttribute("isActive", true);

        // 値比較の例
        model.addAttribute("previous", 100);
        model.addAttribute("current", 100);

        return "sample/handlebars-sample";
    }

    private record Product(String name, int price) {
    }


}
