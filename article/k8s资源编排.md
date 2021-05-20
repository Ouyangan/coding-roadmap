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