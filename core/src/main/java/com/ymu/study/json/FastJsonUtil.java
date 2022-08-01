package com.ymu.study.json;

import org.jfree.data.json.impl.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastJsonUtil {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "BGY202003-BL-202105270119");

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<>();
        m1.put("sort", 1);
        m1.put("contractName", "江门新会骏凯豪庭五期一标A区交付区项目园林绿化（生态）建设工程施工合同");
        m1.put("contractCode", "江门新会骏凯豪庭-工程施工类-配套工程类-2021-0156");
        m1.put("coreName", "江门保利项目公司");
        m1.put("supplier","广州链金科技");
        m1.put("invoiceCode","31502316");
        m1.put("invoiceMoney","9,069,524.00");
        m1.put("money","7,000,000.00");
        m1.put("expirDate","2022-06-22");
        m1.put("remark","/");

        Map<String, Object> m2 = new HashMap<>();
        m2.put("sort", 2);
        m2.put("contractName", "大师傅士大夫士大夫萨芬士大夫");
        m2.put("contractCode", "江门新会骏凯");
        m2.put("coreName", "江门保利项目");
        m2.put("supplier","小草");
        m2.put("invoiceCode","31502316");
        m2.put("invoiceMoney","9,069,524.00");
        m2.put("money","7,000,000.00");
        m2.put("expirDate","2022-06-22");
        m2.put("remark","/");

        list.add(m1);
        list.add(m2);

        jsonObject.put("list1", list);

        System.out.println(jsonObject.toJSONString());
    }
}
