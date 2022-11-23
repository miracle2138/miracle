- 仅需要import springboot validation的jar包即可
- 定义Java bean，基于属性加注解约束条件
- 定义validation的factory，设置验证条件，如短路 以及 国际化等
- 在方法中使用@Valid注解启用
- 国际化搭配messageSource使用
- 分组，如果一个bean在不同接口的校验规则不同，可以通过group来解决
- 必要时可以自定义验证器

[1](https://lionli.blog.csdn.net/article/details/121597834?spm=1001.2101.3001.6650.4&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-4-121597834-blog-101880225.pc_relevant_3mothn_strategy_and_data_recovery&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-4-121597834-blog-101880225.pc_relevant_3mothn_strategy_and_data_recovery&utm_relevant_index=5)

[2](https://blog.csdn.net/qq_34021712/article/details/87895875)