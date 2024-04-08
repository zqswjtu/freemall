**启动nacos server报错：**

![image-20240327195726657](C:\Users\17458\AppData\Roaming\Typora\typora-user-images\image-20240327195726657.png)

因为nacos是默认的集群模式所以我们在startup.cmd里面第28行改成单机模式就好了

![image-20240327195910461](C:\Users\17458\AppData\Roaming\Typora\typora-user-images\image-20240327195910461.png)

**使用nacos注册服务后，远程调用服务报错：**

java.lang.IllegalStateException: No Feign Client for loadBalancing defined. Did you forget to include spring-cloud-starter-loadbalancer?

Spring cloud loadbalancer是spring官方发布的客户端负载均衡组件，而Netflix的 Ribbon已进入维护阶段，从最新的spring cloud2020版本中移除掉了，升级之后Nacos注册中心无法正常运行了，报错提示是否忘了引入Spring cloud loadbalancer

解决方案：在pom.xml中导入下列依赖

```markdown
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
    <version>2.2.2.RELEASE</version>
</dependency>
```

**查询商品菜单分类的时候发现只有二级目录：**

没有使用递归方式进行查询

**前后端跨域问题：**

使用配置类解决

**网关请求转发不起作用：**

注意网关配置路由转发的先后顺序

**OSS整合问题**

注意要带上<version>，否则报错：

```markdown
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alicloud-oss</artifactId>
    <version>2.2.0.RELEASE</version>
</dependency>
```

> Unresolved dependency: 'com.alibaba.cloud:spring-cloud-starter-alicloud-oss:jar:unknown'

重点：需要配置spring.cloud.util:enabled为false，否则如下报错：

***************************
APPLICATION FAILED TO START
***************************

Description:

Parameter 0 of method inetIPv6Utils in com.alibaba.cloud.nacos.util.UtilIPv6AutoConfiguration required a single bean, but 2 were found:
    - spring.cloud.inetutils-org.springframework.cloud.commons.util.InetUtilsProperties: defined in unknown location
    - inetUtilsProperties: defined by method 'inetUtilsProperties' in class path resource [org/springframework/cloud/commons/util/UtilAutoConfiguration.class]


Action:

Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
