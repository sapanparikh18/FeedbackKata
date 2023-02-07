## Micronaut 3.8.3 Documentation

- [User Guide](https://docs.micronaut.io/3.8.3/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.8.3/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.8.3/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

---

- [Jib Gradle Plugin](https://plugins.gradle.org/plugin/com.google.cloud.tools.jib)

## Azure Container Instance Workflow

Workflow file: [`.github/workflows/azure-container-instance.yml`](.github/workflows/azure-container-instance.yml)

### Workflow description

For pushes to the `master` branch, the workflow will:

1. Setup the build environment with respect to the selected java/graalvm version.
2. Login to Docker registry.
3. Login to [Azure Command-Line Interface](https://docs.microsoft.com/cs-cz/cli/azure/).
4. Build, tag and push Docker image with Micronaut application to the Docker Registry.
5. Deploy to [Azure Container Instances](https://docs.microsoft.com/cs-cz/azure/container-instances/).

### Dependencies on other GitHub Actions

- [Login to Docker Registry `docker/login`](https://github.com/docker/login-action)
- [Setup GraalVM `DeLaGuardo/setup-graalvm`](https://github.com/DeLaGuardo/setup-graalvm)
- [Setup Azure CLI `azure/login`](https://github.com/Azure/login)

### Setup

Add the following GitHub secrets:

| Name                   | Description                                                                                                                                                                                                                                                                                        |
|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| DOCKER_USERNAME        | Docker registry username. In case of Azure Container Registry, provide Azure username or Service principal ID, see more on [Azure Container Registry authentication with service principals](https://docs.microsoft.com/en-us/azure/container-registry/container-registry-auth-service-principal). |
| DOCKER_PASSWORD        | Docker registry password. In case of Azure Container Registry, provide Azure password or Service principal password.                                                                                                                                                                               |
| DOCKER_REPOSITORY_PATH | Docker image repository. In case of Azure Container Registry, for image `micronaut.azurecr.io/foo/bar:0.1`, the `foo` is an _image repository_.                                                                                                                                                    |
| DOCKER_REGISTRY_URL    | Docker registry url. In case of Azure Container Registry use the Container registry login path, e.g. for the image `micronaut.azurecr.io/foo/bar:0.1`, the `micronaut.azurecr.io` is a _registry url_.                                                                                             |
| AZURE_CREDENTIALS      | Azure Service Principal, see more on [Azure/aci-deploy#Azure Service Principal for RBAC](https://github.com/Azure/aci-deploy#azure-service-principal-for-rbac).                                                                                                                                    |
| AZURE_RESOURCE_GROUP   | Azure Resource Group name, see more on [Resource groups](https://docs.microsoft.com/en-us/azure/azure-resource-manager/management/overview#resource-groups).                                                                                                                                       |

The workflow file also contains additional configuration options that are now configured to:

| Name            | Description                                                                                                                                                                                                                                                                    | Default value |
|-----------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------|
| AZURE_LOCATION  | Location where the Container Instance will be created. See [Resource availability for Azure Container Instances in Azure regions](https://docs.microsoft.com/en-us/azure//container-instances/container-instances-region-availability) to find out what regions are supported. | `westeurope`  |
| AZURE_DNS_LABEL | The dns name label for container group with public IP.                                                                                                                                                                                                                         | `scromania`   |

### Verification

Call the rest api endpoint `[AZURE_DNS_LABEL].[AZURE_LOCATION].azurecontainer.io:[PORT]/scromania`:

```
curl http://scromania.westeurope.westeurope.azurecontainer.io:8080/scromania
```

## Push To Docker Registry Workflow

Workflow file: [`.github/workflows/gradle.yml`](.github/workflows/gradle.yml)

### Workflow description

For pushes to the `master` branch, the workflow will:

1. Setup the build environment with respect to the selected java/graalvm version.
2. Login to docker registry based on provided configuration.
3. Build, tag and push Docker image with Micronaut application to the Docker container image.

### Dependencies on other GitHub Actions

- [Docker login](`https://github.com/docker/login-action`)(`docker/login`)
- [Setup GraalVM](`https://github.com/DeLaGuardo/setup-graalvm`)(`DeLaGuardo/setup-graalvm`)

### Setup

Add the following GitHub secrets:

| Name                   | Description                                                                                                          |
|------------------------|----------------------------------------------------------------------------------------------------------------------|
| DOCKER_USERNAME        | Username for Docker registry authentication.                                                                         |
| DOCKER_PASSWORD        | Docker registry password.                                                                                            |
| DOCKER_REPOSITORY_PATH | Path to the docker image repository inside the registry, e.g. for the image `foo/bar/micronaut:0.1` it is `foo/bar`. |
| DOCKER_REGISTRY_URL    | Docker registry url.                                                                                                 |

#### Configuration examples

Specifics on how to configure public cloud docker registries like DockerHub, Google Container Registry (GCR), AWS
Container Registry (ECR),
Oracle Cloud Infrastructure Registry (OCIR) and many more can be found
in [docker/login-action](https://github.com/docker/login-action)
documentation.

#### DockerHub

- `DOCKER_USERNAME` - DockerHub username
- `DOCKER_PASSWORD` - DockerHub password or personal access token
- `DOCKER_REPOSITORY_PATH` - DockerHub organization or the username in case of personal registry
- `DOCKER_REGISTRY_URL` - No need to configure for DockerHub

> See [docker/login-action for DockerHub](https://github.com/docker/login-action#dockerhub)

#### Google Container Registry (GCR)

Create service account with permission to edit GCR or use predefined Storage Admin role.

- `DOCKER_USERNAME` - set exactly to `_json_key`
- `DOCKER_PASSWORD` - content of the service account json key file
- `DOCKER_REPOSITORY_PATH` - `<project-id>/foo`
- `DOCKER_REGISTRY_URL` - `gcr.io`

> See [docker/login-action for GCR](https://github.com/docker/login-action#google-container-registry-gcr)

#### AWS Elastic Container Registry (ECR)

Create IAM user with permission to push to ECR (or use AmazonEC2ContainerRegistryFullAccess role).

- `DOCKER_USERNAME` - access key ID
- `DOCKER_PASSWORD` - secret access key
- `DOCKER_REPOSITORY_PATH` - no need to set
- `DOCKER_REGISTRY_URL` - set to `<aws-account-number>.dkr.ecr.<region>.amazonaws.com`

> See [docker/login-action for ECR](https://github.com/docker/login-action#aws-elastic-container-registry-ecr)

#### Oracle Infrastructure Cloud Registry (OCIR)

[Create auth token](https://www.oracle.com/webfolder/technetwork/tutorials/obe/oci/registry/index.html#GetanAuthToken)
for authentication.

- `DOCKER_USERNAME` - username in format `<tenancy>/<username>`
- `DOCKER_PASSWORD` - account auth token
- `DOCKER_REPOSITORY_PATH` - `<tenancy>/<registry>/foo`
- `DOCKER_REGISTRY_URL` - set to `<region>.ocir.io`

>
See [docker/login-action for OCIR](https://github.com/docker/login-action#oci-oracle-cloud-infrastructure-registry-ocir)

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)

## Feature management documentation

- [Micronaut Management documentation](https://docs.micronaut.io/latest/guide/index.html#management)

## Feature github-workflow-azure-container-instance documentation

- [https://docs.github.com/en/free-pro-team@latest/actions](https://docs.github.com/en/free-pro-team@latest/actions)

## Feature github-workflow-docker-registry documentation

- [https://docs.github.com/en/free-pro-team@latest/actions](https://docs.github.com/en/free-pro-team@latest/actions)

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)


