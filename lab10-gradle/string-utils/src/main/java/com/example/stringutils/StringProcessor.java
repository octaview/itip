package com.example.stringutils;

import org.apache.commons.lang3.StringUtils;

public class StringProcessor {
    
    // делаем всякие приколы со строкой
    public static String process(String input) {
        if (StringUtils.isBlank(input)) {
            return "пустота";
        }
        
        // переворачиваем строку задом наперед
        String reversed = StringUtils.reverse(input);
        
        // делаем первую букву заглавной
        return StringUtils.capitalize(reversed);
    }
}