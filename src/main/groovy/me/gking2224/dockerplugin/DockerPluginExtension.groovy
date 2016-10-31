package me.gking2224.dockerplugin

import org.gradle.api.Project
import org.slf4j.LoggerFactory


class DockerPluginExtension {
    
    def host = "unix:///var/run/docker.sock"
    def registryUsername = "AWS"
    def registryPassword
    def registryUrl
    
    
    def logger = LoggerFactory.getLogger(DockerPluginExtension.class)
    
    private static final String KEY = "dockerplugin"
    
    private Project project;
    

    public DockerPluginExtension(Project project) {
        this.project = project;
    }
    
}
