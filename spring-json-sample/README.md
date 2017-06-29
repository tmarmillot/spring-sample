# Spring - JSON

  - 참고: 
    - http://pangyo-dev.tistory.com/2
    - https://gist.github.com/matsev/3672298#file-gettestuserasjson-java

## POM
  - STS SpringMVC 기본 템플릿 POM을 제외한 추가 설정 X)
  - jackson-databind 사용 
  - spring 버전에 따라 jackson-databind 또는 jackson-mapper-asl 사용

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.8.9</version>
</dependency>
```

## GET
``` js
var param = {
        id : $('#id').val(),
        pwd : $('#pwd').val()
    };
$.ajax({
    method : 'get',
    url : '/ajax/testGet',
    dataType : 'json',
    data : param,
    success : function(result) {
    },
    fail : function(result) {
    }
});
```

``` java
@RequestMapping(value = "/ajax/testGet", method = RequestMethod.GET)
@ResponseBody
public Map<String, Object> testGet(@RequestParam("id") String id, @RequestParam("pwd") String pwd) {
    Map<String, Object> result = new HashMap<String, Object>();
    ......
    ......
}
```

## POST
  - contentType, dataType 설정
  - JSON.stringify() 을 사용하여 문자열로 변환 후 전달

### javascript
``` js
var param = {
        id : $('#id').val(),
        pwd : $('#pwd').val()
    };
$.ajax({
    method : 'POST',
    url : '/ajax/testPostStr',
    contentType: 'application/json',
    dataType : 'json',
    data : JSON.stringify(param),
    success : function(result) {
    },
    fail : function(result) {
        console.log(result);
    }
});
```

### POST - String
  - ObjectMapper
``` java
@SuppressWarnings("unchecked")
@RequestMapping(value = "/ajax/testPostStr", method = RequestMethod.POST)
@ResponseBody
public Map<String, Object> testPostStr(@RequestBody String paramStr) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> paramMap = mapper.readValue(paramStr, HashMap.class);
    ......
    ......
}
```


### POST - Map
``` java
@RequestMapping(value = "/ajax/testPostMap", method = RequestMethod.POST)
@ResponseBody
public Map<String, Object> testPostMap(@RequestBody Map<String, Object> param) {
    ......
    ......
	}
```


### POST VO
``` java
@RequestMapping(value = "/ajax/testPostVO", method = RequestMethod.POST)
@ResponseBody
public Map<String, Object> testPostVO(@RequestBody User user) {
    ......
    ......
}
```