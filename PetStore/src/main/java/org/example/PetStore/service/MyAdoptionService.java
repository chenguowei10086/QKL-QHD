package org.example.PetStore.service;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.example.PetStore.utils.WeBASEUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyAdoptionService {
    @Value("${system.contract.adoptionAddress}")
    String contractAddress;
    public static final String ABI = org.example.PetStore.utils.IOUtil.readResourceAsString("abi/Adoption.abi");
    @Autowired
    WeBASEUtils weBASEUtils;
    /**
     * * 领养宠物
     * */
    public Dict adopt(String userAddress, Integer petId) {
        /**
         * * 假设有8 个宠物可供领养，编号为0-7
         * * 输入宠物编号为0-7 之间，若不在此范围内则返回code为500
         * * */
        if (petId < 0 || petId > 7) {
            Dict result = new Dict();
            result.set("code", 500);
            result.set("result","宠物编号不合规");
        }
        /**
         * 向WeBASE-Front 发送Request 请求
         * */
        List funcParam = new ArrayList();
        funcParam.add(petId);
        Dict result = weBASEUtils.commonReq(userAddress, "adopt", funcParam, ABI, "Adoption", contractAddress);
/**
 * 获取返回结果，并解析，如果结果与输入宠物编号相符则返回200
 * */
        JSONObject respBody = JSONUtil.parseObj(result.get("result"));
        String output = (String) respBody.get("output");
        long resInt = HexUtil.hexToLong(output.substring(2));
        result.set("result", resInt);
        if (resInt != petId) {
            result.set("code", resInt);
        }else {
            result.set("code", 200);
        } return result;
    }
    /**
     * 列出宠物领养情况列表
     * */
    public Dict listPetAdoption(String userAddress) {
/**
 * 获取领养宠物的账户地址列表
 * */
        JSONArray resArr2 = getPetAdoptAddresses(userAddress);
        List petStatusList = new ArrayList();
/**
 * 解析账户地址，若为默认地址（0x0000000000000000000000000000000000000000）则表示该编号宠物没有被领养
 * */
        for (Object item : resArr2) {
            String parseAddress = (String) item;
            if (parseAddress.equals("0x0000000000000000000000000000000000000000")) {
                petStatusList.add(0);
            }else {
                petStatusList.add(1);
            }
        } Dict result = new Dict();
        result.set("code", 200);
        result.set("result", petStatusList);
        return result;
    }
    /**
     * 通过地址获取宠物列表，如果获取地址列表不是查询用户，则说明该用
     户未领养此宠物
     * @Param: userAddress 查询用户地址
     * */
    public Dict listAdoptedPet(String userAddress) {
        JSONArray resArr2 = getPetAdoptAddresses(userAddress);
        List petStatusList = new ArrayList();
        for (Object item : resArr2) {
            String parseAddress = (String) item;
            if (parseAddress.equals(userAddress)) {
                petStatusList.add(1);
            }else {
                petStatusList.add(0);
            }
        } Dict result = new Dict();
        result.set("code", 200);
        result.set("result", petStatusList);
        return result;
    }
    /**
     * 通过用户地址获取领养宠物的地址列表
     * */
    public JSONArray getPetAdoptAddresses(String userAddress) {
        List funcParam = new ArrayList();
        Dict result = weBASEUtils.commonReq(
                userAddress, "getAdopters", funcParam,
                ABI, "Adoption", contractAddress);
        JSONArray resArr1 = JSONUtil.parseArray(result.get("result"));
        JSONArray resArr2 = (JSONArray) resArr1.get(0);
        return resArr2;
    }
}