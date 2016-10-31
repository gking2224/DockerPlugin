package me.gking2224.dockerplugin.task

import org.gradle.api.tasks.TaskAction

import com.github.dockerjava.api.command.PushImageCmd
import com.github.dockerjava.api.model.AuthConfig
import com.github.dockerjava.core.command.PushImageResultCallback

class DockerLogin extends AbstractDockerTask {
    
    def imageId
    
    public PushImage() {
    }

    @TaskAction
    def login() {
        auth()
    }
}
