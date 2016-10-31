package me.gking2224.dockerplugin.task;

import me.gking2224.dockerplugin.DockerPluginExtension

import org.gradle.api.DefaultTask

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.model.AuthConfig
import com.github.dockerjava.api.model.AuthResponse
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientBuilder
import com.github.dockerjava.core.DockerClientConfig

public abstract class AbstractDockerTask<T> extends DefaultTask {
    
    DockerClient client
    
    com.github.dockerjava.api.DockerClient getDockerClient() {
        if (client == null) _initClient()
        return client
    }
    
    def auth() {
        
        AuthResponse res = getDockerClient().authCmd().withAuthConfig(getAuthConfig()).exec()
        logger.info("docker authenticated with status: ${res.getStatus()}")
    }
    
    def _initClient() {
        
        logger.info(" login to ${ext.registryUrl} with username ${ext.registryUsername} password ${ext.registryPassword}")
        
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
            .withDockerHost(ext.host)
            .withDockerTlsVerify(false)
//            .withDockerCertPath("/home/user/.docker/certs")
//            .withDockerConfig("/home/user/.docker")
            .withApiVersion("1.23")
            .withRegistryUsername(ext.registryUsername)
            .withRegistryPassword(ext.registryPassword)
            .withRegistryUrl(ext.registryUrl)
            .build();
        client = DockerClientBuilder.getInstance(config).build();
    }
    
    DockerPluginExtension getExt() {
        project.extensions.getByType(DockerPluginExtension.class)
    }
    
    def getRegistryUrl() {
        return getExt().registryEndpoint
    }
    
    def getAuthConfig() {
        AuthConfig ac = new AuthConfig()
        ac.registryAddress = ext.registryUrl
        ac.username = ext.registryUsername
        ac.password = ext.registryPassword
        return ac
    }
}
