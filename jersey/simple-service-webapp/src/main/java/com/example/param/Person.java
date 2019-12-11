package com.example.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

/**
 * 说明：人实体
 * @author carter
 * 创建时间： 2019年12月04日 18:47
 **/
@Data
@ApiModel("人实体")
public class Person {
    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年纪")
    private Integer age;

    public Person() {
    }

    public Person(@NonNull String str) {

        final String[] splitArray = str.split(":", 2);

        assert splitArray.length == 2 : "字符串没有按照格式a:b给出";

        setName(splitArray[0]);
        setAge(Integer.parseInt(splitArray[1]));

    }

}
