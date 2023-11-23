package com.github.cadedi.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

@Data
// @RequiredArgsConstructor
@AllArgsConstructor
// @NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private BigDecimal englishScore;
    private BigDecimal chineseScore;
    private BigDecimal score1;
    private BigDecimal score2;
    private BigDecimal score3;



    public Map<String,BigDecimal> toMap(){
        Map<String, BigDecimal> hashMap = new HashMap<>();

        // 获取 JavaBean 的所有字段
        Field[] fields = this.getClass().getDeclaredFields();

        // 遍历字段，并将字段名和对应的值存入 HashMap
        for (Field field : fields) {
            field.setAccessible(true); // 设置字段可访问
            String fieldName = field.getName();
            try {
                Object value = field.get(this);
                if (value instanceof BigDecimal) {
                    hashMap.put(fieldName, (BigDecimal)value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return hashMap;
    }
    public List<Map.Entry<String, BigDecimal>> getTop3Entry(Map<String,BigDecimal> map){
        List<Map.Entry<String, BigDecimal>> sortedEntries = new ArrayList<>(map.entrySet());
        Collections.sort(sortedEntries, new Comparator<Map.Entry<String, BigDecimal>>() {
            @Override
            public int compare(Map.Entry<String, BigDecimal> entry1, Map.Entry<String, BigDecimal> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        // 获取值大小大于其他字段的三个键值对
        List<Map.Entry<String, BigDecimal>> topThreeEntries = sortedEntries.subList(0, 3);
        return  topThreeEntries;
    }

    public static void main(String[] args) {
        Person li = new Person(1L, "li", new BigDecimal(13.66), new BigDecimal(28), new BigDecimal(37.3), new BigDecimal(6.5),
                new BigDecimal(3.3));
        Map<String, BigDecimal> stringBigDecimalMap = li.toMap();
        List<Map.Entry<String, BigDecimal>> top3Entry = li.getTop3Entry(stringBigDecimalMap);
        for (Map.Entry<String, BigDecimal> entry : top3Entry) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }


        System.out.println(CategoryA.valueOf("chineseScore"));

        for (CategoryA field : CategoryA.values()){
            System.out.println(field.getName());
        }
    }
}

enum CategoryA{
    chineseScore("chineseScore"),
    englishScore("englishScore");
    private final String name;
    private CategoryA(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
enum CategoryB{
    score1,
    score2,
    score3
}


