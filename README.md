## Root project, create OCI images for subprojects with podman

```
./gradlew bootJar
./gradlew podman [-PdockerVersion=1.0.1 ]

# will show local image and ghcr.io tagged image
podman images
```

## Root project, create OCI images for subprojects with docker

```
./gradlew bootJar
./gradlew docker [-PdockerVersion=1.0.1 ]
```

# Creating tag

```
newtag=v1.0.1
git commit -a -m "changes for new tag $newtag" && git push
git tag $newtag && git push origin $newtag
```

# Deleting tag

```
# delete single local tag, then remote
todel=v1.0.1; git tag -d $todel && git push origin :refs/tags/$todel

# delete range of tags
for i in $(seq 1 9); do todel=v1.0.$i; git tag -d $todel && git push origin :refs/tags/$todel; done
```

# Deleting release

```
todel=v1.0.1

# delete release and remote tag
gh release delete $todel --cleanup-tag -y

# delete local tag
git tag -d $todel
```

# Check gradle dependencies
```
./gradlew dependencies
./gradlew dependencies --configuration runtimeClasspath
./gradlew dependencies --configuration compileClasspath
./gradlew dependencies --configuration implementation
```
