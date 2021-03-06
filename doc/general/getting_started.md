# Getting Started

This section documents the steps to get started with OSStreams on a local
Kubernetes deployment. Only MacOS is covered but all instructions are trivially
translatable to GNU/Linux.

## Software installation

### Tools

1. Install [Homebrew](https://brew.sh)
2. Install `kubectl`: `brew install kubectl`
3. Install `redis-cli`: `brew install redis`

### Docker and Kubernetes

1. Visit the [Docker for Mac](https://www.docker.com/docker-mac) website and
   grab the installation package.
2. Install docker and activate kubernetes in docker's preferences.
3. Using the status bar menu, make sure that your Kubernetes profile is set
   to `docker-for-desktop`.
4. Set the number of cores to 2 and the allocated memory to 4GB.

Once installed, you should be able to get the following:

```
$ kubectl get nodes
NAME                 STATUS    ROLES     AGE       VERSION
docker-for-desktop   Ready     master    5d        v1.10.3
```

## Working with the prototype

### Install the custom resource definitions

You need to install _all_ the custom resource definitions located at
`src/java/platform/com.ibm.streams.controller/crds` as follows:

```
$ kubectl apply -f src/java/platform/com.ibm.streams.controller/crds/streams-job.yaml
customresourcedefinition.apiextensions.k8s.io/streamsjobs.streams.ibm.com created
```

Note that applying the custom resource definitions to your Kubernetes instance
persists across starting and stopping Kubernetes itself. However, if we change
these definitions during development, we need to re-apply them.

### Install the role-based credentials

Similarly to the CRDs, you need to install _all_ the RBAC definitions located at
`src/java/platform/com.ibm.streams.controller/templates/rbacs`.

### Install the controller

Install the controller using the following deployment file:

```
kubectl apply -f src/java/platform/com.ibm.streams.controller/templates/operators/streams-instance-operator.yaml
```

### Create a Streams job

#### Application bundles

Application bundles are automatically loaded from a GitHub URL. When loading a
bundle from a private GitHub account, you will need a personal token. To use
this token, create a `Secret` with a `token` field that contains your token:

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: github-token
type: Opaque
stringData:
  token: {{MY_GITHUB_TOKEN}}
```

Then, use the name of this secret in the bundle's configuration of your job:

```yaml
apiVersion: streams.ibm.com/v1
kind: Job
metadata:
  name: my-job
spec:
  bundle:
    name: some.bundle.Main.sab
    github:
      url: https://my/github/url/some.bundle.Main.sab
      secret: github-token
```

The URL must be compliant with the GitHub API v3 format:

```
https://api.${GITHUB_URL}/repos/${USER}/${REPOSITORY}/contents/${PATH_TO_BUNDLE}.sab
```

### Using the metrics

We use `Prometheus` to scrap the runtime metrics and `Grafana` to display them.
Declarations to use them are located here:

```
src/java/platform/com.ibm.streams.controller/templates/metrics
```

First, install the following:

```
$ kubectl apply -f prometheus-operator.yaml
$ kubectl apply -f prometheus-rbac.yaml
$ kubectl apply -f streams-metrics.yaml
```

Then, using your web browser, navigate to `localhost:30800` for the `Prometheus`
console, and `localhost:30900` for the `Grafana` console.

### Using local storage

For local development, it is useful to use local storage to provide the `data`
volume as well as expose local application bundles to the operator. To that end,
the following template creates the necessary `PersistentVolume` and
`PersistentVolumeClaim`:

```
src/java/platform/com.ibm.streams.controller/templates/volumes/local-volume.yaml
```

The following steps explain how to set these resources up and bind them to the
Streams operator and processing elements.

#### Preparation

1. Create a `/k8s` directory with world read, write, and execute permissions
2. Create a `/k8s/data` and a `/k8s/sabs` directories with the same permissions
3. Place your SABs in the `/k8s/sabs` directory
4. Change the node name in `local-storage.yaml` with your own
5. Apply the `local-storage.yaml` file

#### Update the Streams operator deployment file

```yaml
apiVersion: apps/v1
kind: Deployment
...
spec:
  ...
  template:
    ...
    spec:
      ...
      containers:
        - name: controller
          ...
          volumeMounts:
            - mountPath: /sabs
              name: local-volume
              subPath: sabs
        - name: repository
          ...
      volumes:
        - name: local-volume
          persistentVolumeClaim:
            claimName: local-volume-claim
```

#### Update the job definition

```yaml
apiVersion: streams.ibm.com/v1
kind: Job
metadata:
  name: parallel
spec:
  bundle:
    name: apps.parallel.Parallel.sab
    file:
      path: /sabs/apps.parallel.Parallel.sab
  processingElement:
    dataVolumeClaim:
      name: local-volume-claim
      subPath: data
  submissionTimeValues:
    width1: 2
    width2: 5
    width3: 4
```
