### yaml创建方法

1. 通过kubectl快速创建`kubectl create deployment antest --image=antest -o yaml --dry-run=none  >antest.yaml`

```
apiVersion: apps/v1
kind: Deployment
metadata:
  creationTimestamp: "2021-05-20T08:16:06Z"
  generation: 1
  labels:
    app: antest
  name: antest
  namespace: default
  resourceVersion: "38299"
  uid: aa5e0276-50b4-45d5-8c4d-e795375e170b
spec:
  progressDeadlineSeconds: 600
  replicas: 1
  revisionHistoryLimit: 10
  selector:
    matchLabels:
      app: antest
  strategy:
    rollingUpdate:
      maxSurge: 25%
      maxUnavailable: 25%
    type: RollingUpdate
  template:
    metadata:
      creationTimestamp: null
      labels:
        app: antest
    spec:
      containers:
      - image: antest
        imagePullPolicy: Always
        name: antest
        resources: {}
        terminationMessagePath: /dev/termination-log
        terminationMessagePolicy: File
      dnsPolicy: ClusterFirst
      restartPolicy: Always
      schedulerName: default-scheduler
      securityContext: {}
      terminationGracePeriodSeconds: 30
status: {}
```

### pod

#### 基本概念

1. 最小部署单元
2. 包含一组容器
3. 容器间共享网络和存储空间
4. 完整生命周期

#### 实现机制

1. 共享网络 \
   通过Pause容器，把其他业务容器加入到Pause容器中，同一个命名空间下可以实现网络共享。

2. 共享存储 \
   引入数据卷Volumn，进行持久化存储。

#### 配置项

- 镜像拉取策略
- 重启策略
- 调度策略
- 资源限制

### Deployment

1. 什么是Controller

- 确保副本数量
- 无状态应用部署
- 有状态应用部署

2. Pod和Controller关系

- controller管理pod
    - 通过标签建立关联关系

3. Deployment有什么作用?

- 应用场景
    - 无状态应用部署
    - 管理pod
    - 部署,滚动升级,伸缩

- 部署
    - kubectl apply -f xx.yaml

- 暴露服务
    - kubectl expose deployment deployment-test --port=80 --type=NodePort --target-port=8002 --name=web -o yaml >
      web1.yaml
    - kubectl apply -f web1.yaml
- 回滚
    - kubectl rollout -h

- 弹性伸缩
    - kubectl scale -h

### Service

1. 为什么需要Service
    - 服务注册
    - 服务负载均衡

2. Pod和Service关系
    - Service管理pod的服务发现和负载均衡
    - 通过标签实现
    
3. 单独对外暴露端口(vip)内部负载均衡将流量路由到pod

