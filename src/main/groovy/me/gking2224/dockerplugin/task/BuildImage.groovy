package me.gking2224.dockerplugin.task

import org.gradle.api.tasks.TaskAction

import com.github.dockerjava.api.command.BuildImageCmd
import com.github.dockerjava.core.command.BuildImageResultCallback

class BuildImage extends AbstractDockerTask {

    File dockerFile
    String tag = "${project.group}/${project.name}:${project.version}"
    File baseDirectory = project.projectDir
    
    String imageId
    
    public BuildImage() {
    }

    @TaskAction
    def buildImage() {
        BuildImageCmd cmd = super.getDockerClient().buildImageCmd()
        if (dockerFile != null) cmd.withDockerfile(dockerFile)
        cmd.withTag(tag)
        cmd.withBaseDirectory(baseDirectory)
        
        def callback = new BuildImageResultCallback()
        imageId = cmd.exec(callback).awaitImageId()
        
    }
    
}
