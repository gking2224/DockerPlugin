package me.gking2224.dockerplugin.task

import org.gradle.api.tasks.TaskAction

import com.github.dockerjava.api.command.PushImageCmd
import com.github.dockerjava.api.model.AuthConfig
import com.github.dockerjava.core.command.PushImageResultCallback

class PushImage extends AbstractDockerTask {
    
    def imageId
    
    public PushImage() {
    }

    @TaskAction
    def pushImage() {
        logger.info("Pushing image $imageId")
        auth()
        PushImageCmd cmd = super.getDockerClient().pushImageCmd(imageId);
        cmd.withAuthConfig(getAuthConfig())
        
        PushImageResultCallback callback = new PushImageResultCallback()
        cmd.exec(callback)
        callback.awaitSuccess()
    }
}
