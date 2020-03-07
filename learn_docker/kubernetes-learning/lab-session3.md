You will need to do the following:

Deploy the Stan's Robot Shop app to the cluster.
Scale up the MongoDB service to two replicas instead of just one.
Once the app is deployed, you should be able to reach it in a browser using the Kube master node's public IP: http://$kube_master_public_ip:30080


If you need additional guidance, click the icon next to each task below for more information on how to complete each task. You can also check out the solution video for a detailed walkthrough.

## Objectives

help
Deploy the Stan's Robot Shop app to the cluster.
Clone the Git repo that containts the pre-made descriptors:
```
cd ~/
git clone https://github.com/linuxacademy/robot-shop.git
```

Since this application has many components, it is a good idea to create a separate namespace for the app:

```
kubectl create namespace robot-shop
```

Deploy the app to the cluster:
```
kubectl -n robot-shop create -f ~/robot-shop/K8s/descriptors/
```

Check the status of the application's pods:
```
kubectl get pods -n robot-shop
```
You should be able to reach the robot shop app from your browser using the Kube master node's public IP: 
```
http://$kube_master_public_ip:30080
```

help
Scale up the MongoDB deployment to two replicas instead of just one.

Edit the deployment descriptor:
```
kubectl edit deployment mongodb -n robot-shop
```

You should see some YAML describing the deployment object.

Under spec:, look for the line that says replicas: 1 and change it to replicas: 2.
Save and exit.

Check the status of the deployment with:
```
kubectl get deployment mongodb -n robot-shop
```

After a few moments, the number of available replicas should be 2.
