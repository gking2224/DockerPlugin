package me.gking2224.dockerplugin

import org.gradle.api.Plugin
import org.gradle.api.Project


class DockerGradlePlugin implements Plugin<Project> {

    private static final String NAME = "me.gking2224.dockerplugin"

	void apply(Project project) {
        
        // declare config extension
		project.extensions.create(DockerPluginExtension.KEY, DockerPluginExtension, project)
        
//        // define tasks
//		project.task(EC2DescribeInstancesTask.NAME, type:EC2DescribeInstancesTask)
	}
}

