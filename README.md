### 消息推送
#### 1.消息推送长链接的集群设计
方案一.  
    没有点对点推送，只有针对群成员的广播，可以用emqtt   
	优点：拿来即用，单机支持10w+的并发，支持集群条件下的共享消息订阅     
        缺点：后期扩展性差，由于是基于订阅模式的消息推送，点对点推送有缺陷（频繁的创建删除topic，会有bug）   
方案二.         
    使用mq作为消息中转    
	优点：业务层不用关心链接    
	缺点：优点即缺点，每个节点都要消费所有的消息      
方案三.       
    自研   
	优点：灵活   
	缺点：暂时没有开源的java的长连接网关项目参照   
	
#### 2.红包广播消息的可达
消息成对
客户端重连时拉取

### 红包相关
#### 1.消减锁粒度，减少竞争
方案一.     
发红包时，进行预处理，将红包提前拆分好，放入缓存；   
方案二.    
预处理时，只是将红包的总金额，个数放入缓存，减少存储。抢红包的请求入队列，保证顺序执行。   
http存在阻塞，这个方案不建议用http方式   
#### 2.数据一致性
保证单个服务数据一致
### 代码目录结构
dtos：数据传输对象    
domain：领域层，包括持久化，远程接口。。。    
service：业务逻辑层   
common：通用层   
facade：对外接口层，http，socket，websocket等方式提供给外部的调用   
im：长连接推送   
