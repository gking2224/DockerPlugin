package me.gking2224.dockerplugin.task

import org.gradle.api.tasks.TaskAction

import com.github.dockerjava.api.command.TagImageCmd

class TagImage extends AbstractDockerTask {
    
    def imageId = project.group + "/" + project.name + ":" + project.version
    def tag
    def registry
    
    public TagImage() {
    }

    @TaskAction
    def tagImage() {
        logger.info("Tagging image $imageId with registry $registry and tag $tag")
        TagImageCmd cmd = super.getDockerClient().tagImageCmd(imageId, registry, tag)
        cmd.exec()
    }
}
