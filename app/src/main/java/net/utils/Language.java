package net.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Language {
    private static Language instance = null;

    private Language() {
    }

    public static Language getInstance() {
        if (instance == null) {
            instance = new Language();
        }
        return instance;
    }

    public HashMap<String, String> getLanguage() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Chinese", "zh");
        map.put("Arabic", "ar");
        map.put("Catalan", "ca");
        map.put("Czech", "cs");
        map.put("danish", "da");
        map.put("German", "de");
        map.put("Greek", "el");
        map.put("English", "en");
        map.put("Esperanto", "eo");
        map.put("Spanish", "es");
        map.put("Basque", "eu");
        map.put("farsi", "fa");
        map.put("Finnish", "fi");
        map.put("French", "fr");
        map.put("Hebrew", "he");
        map.put("Croatian", "hr");
        map.put("Hungarian", "hu");
        map.put("Bahasa Indonesia", "id");
        map.put("Italy", "it");
        map.put("Japanese", "ja");
        map.put("Kartuli", "ka");
        map.put("Korean", "ko");
        map.put("Lithuanian", "lt");
        map.put("Macedonian", "mk");
        map.put("Malaysia", "ms");
        map.put("Dutch", "nl");
        map.put("Norwegian", "no");
        map.put("polish", "pl");
        map.put("Portugal", "pt");
        map.put("Romanian", "ro");
        map.put("Russian", "ru");
        map.put("Slovenian", "sl");
        map.put("Swedish", "sv");
        map.put("Tamil", "ta");
        map.put("Thai", "th");
        map.put("Turkish", "tr");
        map.put("Ukrainian", "uk");
        map.put("Vietnamese", "vi");
        map.put("Cantonese", "zh-yue");
        return map;
    }

    public ArrayList<String> getAllCountry(HashMap<String, String> data) {
        ArrayList<String> list = null;
        if (data == null || data.size() == 0) {
            return null;
        } else {
            list = new ArrayList<>();
            for (String item : data.keySet()){
                list.add(item);
            }
        }
        return list;
    }

    public String getCode(HashMap<String, String> data, String country) {
        String code = "";
        if (TextUtils.isEmpty(country)){
            return "";
        }
        for (String item : data.keySet()) {
            if (TextUtils.equals(item, country)) {
                code = data.get(item);
                break;
            }
        }
        return code;
    }

    public String getCountry(HashMap<String, String> data,String code){
        String country = "";
        if (TextUtils.isEmpty(code)){
            return "";
        }

        for (String item : data.keySet()){
            if (TextUtils.equals(data.get(item),code)){
                country = item;
                break;
            }
        }

        return country;
    }
}
