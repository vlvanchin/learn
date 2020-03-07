# Lab session 1 on Kubernetes

## Step 1: For Docker

Do the following on all three nodes:
```
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository \
"deb [arch=amd64] https://download.docker.com/linux/ubuntu \
$(lsb_release -cs) \
stable"
```

```
sudo apt-get update
sudo apt-get install -y docker-ce=18.06.1~ce~3-0~ubuntu
sudo apt-mark hold docker-ce
```

Verify that Docker is up and running with:
`sudo systemctl status docker`

Make sure the Docker service status is active (running)!


## Step 2 : Install Kubeadm, Kubelet, and Kubectl on all three nodes.
Install the Kubernetes components by running this on all three nodes:
```
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
cat << EOF | sudo tee /etc/apt/sources.list.d/kubernetes.list
deb https://apt.kubernetes.io/ kubernetes-xenial main
EOF
```

```
sudo apt-get update
sudo apt-get install -y kubernetes-cni=0.6.0-00
sudo apt-get install -y kubelet=1.12.2-00 kubeadm=1.12.2-00 kubectl=1.12.2-00
sudo apt-mark hold kubelet kubeadm kubectl
```

## step 3 : Bootstrap the cluster on the Kube master node.
On the Kube master node, do this:
```
sudo kubeadm init --pod-network-cidr=10.244.0.0/16
```
That command may take a few minutes to complete.

When it is done, set up the local kubeconfig:
```
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
```

Take note that the kubeadm init command printed a long kubeadm join command to the screen. You will need that kubeadm join command in the next step!

Run the following commmand on the Kube master node to verify it is up and running:

`kubectl version`

This command should return both a Client Version and a Server Version.


## Step 4 : Join the two Kube worker nodes to the cluster.
Copy the `kubeadm join` command that was printed by the `kubeadm init` command earlier, with the token and hash. Run this command on both worker nodes, but make sure you add sudo in front of it:

```
sudo kubeadm join $some_ip:6443 --token $some_token --discovery-token-ca-cert-hash $some_hash
```

Now, on the Kube master node, make sure your nodes joined the cluster successfully:
```
kubectl get nodes
```

Verify that all three of your nodes are listed. It will look something like this:
```
NAME            STATUS     ROLES    AGE   VERSION
ip-10-0-1-101   NotReady   master   30s   v1.12.2
ip-10-0-1-102   NotReady   <none>   8s    v1.12.2
ip-10-0-1-103   NotReady   <none>   5s    v1.12.2
```
Note that the nodes are expected to be in the NotReady state for now.

## step 5 : Set up cluster networking with flannel

Turn on iptables bridge calls on all three nodes:

```
echo "net.bridge.bridge-nf-call-iptables=1" | sudo tee -a /etc/sysctl.conf
sudo sysctl -p
```

Next, run this only on the Kube master node:

```
kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/bc79dd1505b0c8681ece4de4c0d86c5cd2643275/Documentation/kube-flannel.yml
```

Now flannel is installed! Make sure it is working by checking the node status again:
`kubectl get nodes`

After a short time, all three nodes should be in the Ready state. If they are not all Ready the first time you run kubectl get nodes, wait a few moments and try again. It should look something like this:
```
NAME            STATUS   ROLES    AGE   VERSION
ip-10-0-1-101   Ready    master   85s   v1.12.2
ip-10-0-1-102   Ready    <none>   63s   v1.12.2
ip-10-0-1-103   Ready    <none>   60s   v1.12.2
```
### Topic for pods:

```
Here are the commands used in this lesson:

Create a simple pod running an nginx container:
cat << EOF | kubectl create -f -
apiVersion: v1
kind: Pod
metadata:
  name: nginx
spec:
  containers:
  - name: nginx
    image: nginx
EOF
```

Get a list of pods and verify that your new nginx pod is in the Running state:
	`kubectl get pods`
	
Get more information about your nginx pod:
	`kubectl describe pod nginx`
	
Delete the pod:
	`kubectl delete pod nginx`
	
## Topic for Clustering and NOdes

Here are the commands used in this lesson:

Get a list of nodes:
	kubectl get nodes
Get more information about a specific node:
	kubectl describe node $node_name


### Topic Networks

Networking is an important part of understanding the basics of Kubernetes. This lesson provides a high-level overview of what a Kubernetes virtual cluster network looks like. We will also demonstrate how the network functions by contacting one pod from another pod over the virtual network.

Create a deployment with two nginx pods:
```
cat << EOF | kubectl create -f -
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx
  labels:
    app: nginx
spec:
  replicas: 2
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx:1.15.4
        ports:
        - containerPort: 80
EOF
```

Create a busybox pod to use for testing:
```
cat << EOF | kubectl create -f -
apiVersion: v1
kind: Pod
metadata:
  name: busybox
spec:
  containers:
  - name: busybox
    image: radial/busyboxplus:curl
    args:
    - sleep
    - "1000"
EOF
```

Get the IP addresses of your pods:

`kubectl get pods -o wide`

Get the IP address of one of the nginx pods, then contact that nginx pod from the busybox pod using the nginx pod's IP address:

`kubectl exec busybox -- curl $nginx_pod_ip`

Topic Architecture

Here are the commands used in this lesson:

Get a list of system pods running in the cluster:

```
kubectl get pods -n kube-system
```

Check the status of the kubelet service:
```
sudo systemctl status kubelet
```

### Topic Deployments

Here are the commands used in this lesson:

Create a deployment:

```
cat <<EOF | kubectl create -f -
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment
  labels:
    app: nginx
spec:
  replicas: 2
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx:1.15.4
        ports:
        - containerPort: 80
EOF
```

Get a list of deployments:
`kubectl get deployments`

Get more information about a deployment:
`kubectl describe deployment nginx-deployment`

Get a list of pods:
`kubectl get pods`

You should see two pods created by the deployment.

### Topic Servies

Here are the commands used in the demonstration:

Create a NodePort service on top of your nginx pods:

```
cat << EOF | kubectl create -f -
kind: Service
apiVersion: v1
metadata:
  name: nginx-service
spec:
  selector:
    app: nginx
  ports:
  - protocol: TCP
    port: 80
    targetPort: 80
    nodePort: 30080
  type: NodePort
EOF
```

Get a list of services in the cluster.

`kubectl get svc`

You should see your service called nginx-service.
Since this is a NodePort service, you should be able to access it using port 30080 on any of your cluster's servers. You can test this with the command:
```
curl localhost:30080
```

You should get an HTML response from nginx!





