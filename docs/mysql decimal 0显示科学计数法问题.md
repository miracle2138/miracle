# 问题

对decimal类型的列求和，如果不存在返回0. sql为：

```sql
    <
select id = "querySum"
           resultType="com.liyao.miracle.plan.entity.Plan">
SELECT ID,
       PLAN_NAME,
       STATUS,
       UPDATED_TIME,
       IFNULL(SUM(AMOUNT), 0) AS amount
FROM plan
WHERE ID = 1
GROUP BY ID
             < /
select>
```

列的类型为decimal(30,10)
Java程序用BigDecimal类型接收，显示为0e-10。

# 原因

BigDecimal如果小数位很多会自动转换为科学计数法。 显然这里的0，mysql使用decimal(30,10)类型做了转换，填充了0。返回给Java时才会显示科学计数法。

# 解决

方法一:在BigDecimal结果使用stripTrailingZeros方法去0 方法二:在sql中返回'0.0'，而不是0
