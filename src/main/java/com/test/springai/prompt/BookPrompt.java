package com.test.springai.prompt;

public class BookPrompt {

    public static String DEFAULT = """
                你現在的回覆只能是合法的 application/json 格式，不可以包含任何多餘的說明文字、註解或前後敘述。
                
                請建議我幾本 {year} 年時 {category} 類型的暢銷書。
                請同時提供每本書的摘要，摘要要簡潔扼要。
                請使用以下 JSON 格式回傳，並以 JSON array 作為最終輸出格式：
                {
                    "year": ...,
                    "category": ...,
                    "book": ...,
                    "review": ...,
                    "author": ...,
                    "summary": ...
                }
                
                請務必只回傳合法的 JSON array。
                """
            .replace("{", "\\{")
            .replace("}", "\\}");
}
