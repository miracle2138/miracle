# logback总结

## spring-boot默认集成了slf4j与logback

## 如何将error和info日志区分？

定义两个appender，info的appender使用LevelFilter，error的appender使用ThresholdFilter

## layout定义日志格式，appender定义输出策略，一般需要区分error和info，且设置为rolling策略，可以删除和滚动

## root引用appender。默认所有的logger都会用这些配置，如果有特殊需求可以定义新的logger