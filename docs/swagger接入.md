# Swagger

- 引入依赖
- 因为springboot2.6和swagger不兼容，需要在yml里加入path规则
- controller的path需要添加method，否则全部method都生成
- @Configuration配置类