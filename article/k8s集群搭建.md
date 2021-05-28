### 硬件要求

1. cpu > 2
2. memory > 2G
3. 集群机器网络可互相访问
4. 唯一的hostname
5. 禁止swap

### 系统配置

1. 系统版本

```
ubuntu20.04.2
```

2. 确保每个节点上 MAC 地址和 product_uuid 的唯一性
   `sudo cat /sys/class/dmi/id/product_uuid`

2. 关闭防火墙(nftables后端兼容性问题，产生重复的防火墙规则)

```
ufw status
ufw disbale
ufw reset
```

3. 关闭selinux(允许容器访问宿主机的文件系统)

默认关闭

4. 关闭swap(不关闭无法启动k8s集群)

```
#临时
sudo su swapoff -a
#永久
vi /etc/fstab
/dev/mapper/centos-swap swap swap default 0 0
```

5. 允许iptables检查桥接流量

```
cat <<EOF | sudo tee /etc/modules-load.d/k8s.conf
br_netfilter
EOF

cat <<EOF | sudo tee /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sudo sysctl --system
```

6. 时间同步

```
apt install ntpdate
ntpdate time.windows.com
```

7. 宿主机配置主从节点 host 映射

 ```
192.168.241.141 master1
192.168.241.142 node1
192.168.241.143 node2
```

8. 配置docker镜像源

```
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
"registry-mirrors": ["https://阿里云私有.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

### 安装kubeadm,kubectl,kubelet

```1
#修改镜像来源
vim /etc/apt/sources.list //末尾添加
# kubeadm及kubernetes组件安装源
deb https://mirrors.aliyun.com/kubernetes/apt kubernetes-xenial main
apt update
apt install kubeadm kubelet kubectl
systemctl enable kubelet
```

- kubectl自动补全

> https://kubernetes.io/zh/docs/tasks/tools/included/optional-kubectl-configs-bash-linux/

### 配置集群

1. 初始化master

```
kubeadm init \
--image-repository registry.aliyuncs.com/google_containers \
--pod-network-cidr=10.244.0.0/16
```

> Your Kubernetes control-plane has initialized successfully!
To start using your cluster, you need to run the following as a regular user:
\
mkdir -p $HOME/.kube sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config sudo chown $(id -u):$(id -g) $HOME/.kube/config
\
Alternatively, if you are the root user, you can run:
\
export KUBECONFIG=/etc/kubernetes/admin.conf
\
You should now deploy a pod network to the cluster. Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
https://kubernetes.io/docs/concepts/cluster-administration/addons/
Then you can join any number of worker nodes by running the following on each as root:
\
kubeadm join 192.168.241.141:6443 --token xtbzom.iu74b3mdi2c4pdyp \
--discovery-token-ca-cert-hash sha256:b665c620a7b4848f10e0a4422f0c4d47223de74b1648abe6ecde625239582ecd

注意上面输出的日志

```
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
export KUBECONFIG=/etc/kubernetes/admin.conf
```

node节点加入命令

```
kubeadm join 192.168.241.141:6443 --token xtbzom.iu74b3mdi2c4pdyp \
--discovery-token-ca-cert-hash sha256:b665c620a7b4848f10e0a4422f0c4d47223de74b1648abe6ecde625239582ecd
```

2. 增加node节点

```
kubeadm join 192.168.241.141:6443 --token xtbzom.iu74b3mdi2c4pdyp \
--discovery-token-ca-cert-hash sha256:b665c620a7b4848f10e0a4422f0c4d47223de74b1648abe6ecde625239582ecd
```

3. 安装网络插件

```
#复制 https://github.com/flannel-io/flannel/blob/master/Documentation/kube-flannel.yml
kubectl apply -f kube-flannel.yml
```

4. 查看系统pod状态

```
kubectl get pods -n kube-system

NAME                              READY   STATUS              RESTARTS   AGE
coredns-545d6fc579-7bstt          0/1     ContainerCreating   0          38m
coredns-545d6fc579-hkmph          0/1     ContainerCreating   0          38m
etcd-master1                      1/1     Running             0          38m
kube-apiserver-master1            1/1     Running             0          38m
kube-controller-manager-master1   1/1     Running             0          38m
kube-flannel-ds-6llck             0/1     CrashLoopBackOff    7          12m
kube-flannel-ds-mbxng             0/1     CrashLoopBackOff    7          12m
kube-flannel-ds-mxnzd             0/1     CrashLoopBackOff    7          12m
kube-proxy-t6rxb                  1/1     Running             0          29m
kube-proxy-xdzkv                  1/1     Running             0          29m
kube-proxy-zl826                  1/1     Running             0          38m
kube-scheduler-master1            1/1     Running             0          38m
```

查看列表发现有pod启动失败
> 查询日志 \
kubectl --namespace kube-system logs kube-flannel-ds-6llck kubectl --namespace kube-system logs kube-flannel-ds-mbxng kubectl --namespace kube-system logs kube-flannel-ds-mxnzd \
失败原因是:集群初始化时没有指定pod网络分配策略(pod-network-cidr=10.244.0.0/16)

解决方法:
> 只能重新初始化集群 master和node节点分别执行 kubeadm reset

#### 部署Dashboard

1. `kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.2.0/aio/deploy/recommended.yaml`
2. 查看部署状态`kubectl get pod --all-namespaces`

```
NAMESPACE              NAME                                         READY   STATUS    RESTARTS   AGE
kube-system            coredns-545d6fc579-74t6p                     1/1     Running   0          20h
kube-system            coredns-545d6fc579-px8pv                     1/1     Running   0          20h
kube-system            etcd-master1                                 1/1     Running   0          20h
kube-system            kube-apiserver-master1                       1/1     Running   0          20h
kube-system            kube-controller-manager-master1              1/1     Running   0          20h
kube-system            kube-flannel-ds-djkth                        1/1     Running   0          20h
kube-system            kube-flannel-ds-nvxq2                        1/1     Running   0          20h
kube-system            kube-flannel-ds-vvwr4                        1/1     Running   0          20h
kube-system            kube-proxy-5lck4                             1/1     Running   0          20h
kube-system            kube-proxy-fwc5t                             1/1     Running   0          20h
kube-system            kube-proxy-gqjg9                             1/1     Running   0          20h
kube-system            kube-scheduler-master1                       1/1     Running   0          20h
kubernetes-dashboard   dashboard-metrics-scraper-856586f554-zbt5v   1/1     Running   0          19m
kubernetes-dashboard   kubernetes-dashboard-78c79f97b4-m294j        1/1     Running   0          19m

```

3. 暴露代理服务`kubectl proxy --address='0.0.0.0'  --accept-hosts='^*$'`
4.
获取token`kubectl -n kube-system describe $(kubectl -n kube-system get secret -n kube-system -o name | grep namespace) | grep token`
5. 访问
   `http://192.168.241.141:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/`
6. 注意使用VMware部署集群时需要通过*虚拟网络编辑器*将虚拟机端口映射一下

