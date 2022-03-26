package org.example.PetStore.controller;


import cn.hutool.core.lang.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.PetStore.service.MyAdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "领养Controller", tags = {"领养Api"})

@RestController
@RequestMapping("myAdoption")
public class MyAdoptionController {
    @Autowired
    MyAdoptionService myAdoptionService;
    @ApiOperation(value = "领养宠物", notes = "领养宠物")
    @RequestMapping(value = "adopt", method = RequestMethod.GET) public Dict adopt(@RequestParam("address") String address, @RequestParam("petId") Integer petId) {
        return myAdoptionService.adopt(address, petId);
    }
    @ApiOperation(value = "列出宠物被领养的情况列表", notes = " 列出宠物被领养的情况列表")
    @RequestMapping(value = "listPetAdoption", method = RequestMethod.GET) public Dict listPetAdoption(@RequestParam("address") String address) {return myAdoptionService.listPetAdoption(address);
    }
    @ApiOperation(value = "列出请求账户已领养宠物的情况", notes = "列出请求账户已领养宠物的情况")
    @RequestMapping(value = "listAdoptedPet", method = RequestMethod.GET)
    public Dict listAdoptedPet(@RequestParam("address") String address) {
        return myAdoptionService.listAdoptedPet(address);
    }
}