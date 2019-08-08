package io.rongxie.ppmtool.services;

import io.rongxie.ppmtool.domain.Project;
import io.rongxie.ppmtool.exceptions.ProjectIdException;
import io.rongxie.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        //logic
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already existed");
        }


    }
    public Project findProjectByProjectIdentifier(String projectId){
        Project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project==null){
            throw new ProjectIdException("Project with ID'"+ projectId.toUpperCase()+ "' Not Found");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deletProjectByIdentifier(String projectId){
        Project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project==null){
            throw new ProjectIdException("Can not delete project with ID'"+projectId.toUpperCase()+"'. This project does not exist");
        }
        projectRepository.delete(project);
    }


}
