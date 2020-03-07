## add kubernetes repository to the centos7 system

```
cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://packages.cloud.google.com/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://packages.cloud.google.com/yum/doc/yum-key.gpg https://packages.cloud.google.com/yum/doc/rpm-package-key.gpg
exclude=kube*
EOF
```

```
cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://packages.cloud.google.com/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://packages.cloud.google.com/yum/doc/yum-key.gpg
        https://packages.cloud.google.com/yum/doc/rpm-package-key.gpg
EOF
```



## to install kubelet, kubeadm and kubectl
```
// to install specific version, use kubelet=1.12.7-00 kubeadm=1.12.7-00 kubectl=1.12.7-00
yum install -y kubelet kubeadm kubectl

// start the services, docker and kubelet
systemctl start docker && systemctl enable docker
systemctl start kubelet && systemctl enable kubelet

//to check the cgroup of docker
docker info | grep -i cgroup

// the docker and kubernetes must be in the same cgroupfs
sed -i 's/cgroup-driver=systemd/cgroup-driver=cgroupfs/g' /etc/systemd/system/kubelet.service.d/10-kubeadm.conf

// to reload the systemd and restart kubelet service
systemctl daemon-reload
systemctl restart kubelet
```
