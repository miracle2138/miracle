# @Mapper导致的bean冲突问题

## 现象

基于Spring写业务逻辑时，定义一个interface，然后写一个实现类，只有实现类会用@Service标注，成为一个bean。
但是今天发现interface也被扫描成一个bean，使用@Autowired注解import时，报bean重复的异常。 查了很久，发现是Mybatis的锅@Mapper的锅。 详细报错：

```
No qualifying bean of type 'com.liyao.miracle.test.service.IMyService' available: expected single matching bean but found 2: myServiceImpl,IMyService
```

代码如下：

```java
public interface IMyService {
    void justForTest();
}

@Service
public class MyServiceImpl implements IMyService {
    @Override
    public void justForTest() {
    }
}
```

代码角度看，IMyService绝对不会是一个bean，因为没有被注册。

查阅资料，发现是springboot启动类定义的@MapperScan注解导致的，该注解会将指定 的包路径下的interface自动注册为bean，原因可能是mybatis
extension的功能，具体没看。 而我这里的IMyService恰巧也在这个路径下，所以报错了。

## 解决办法

那么如何解决？

### 解法一

最小化@MapperScan类路径，仅仅包含Mapper接口所在的包即可。

### 解法二

使用@Mapper注解即可，二者作用等价。@Mapper标注的类也会被自动扫描为mapper实例。推荐解法二。

@MapperScan注解会被`MapperScannerConfigurer`类处理。

```java
    scanner.scan(
        StringUtils.tokenizeToStringArray(this.basePackage,ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
```

这里会进行一次扫描，将interface的类实例化为bean。大部分方法复用的父类，覆写了部门bean资质的代码。