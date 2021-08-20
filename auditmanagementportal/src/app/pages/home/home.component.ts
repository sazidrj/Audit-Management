import { QuestionsComponent } from './../questions/questions.component';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/classes/question';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {


  AuditType={
     auditType:""
  }

  ProjectDetails={
       projectName:"",
       projectManagerName: "",
       applicationOwnerName: "",
  }

  questionsEntity: Question[] = []; 

 

  constructor(private homeService:HomeService, private router:Router) { }

  ngOnInit(): void {
    window.onpopstate = function () { 
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
    window.location.href="/";
  }
}

  onSubmit(){
   
    this.homeService.getQuestions(this.AuditType, this.ProjectDetails).subscribe(
      (data:any)=>{
        console.log(data);
        this.questionsEntity = data.questionsEntity;  
        this.router.navigate(["questions"],{
          state:
             {questionsList:this.questionsEntity, auditType:this.AuditType}
        });
      },
      (error)=>{
          if(error.status===500)
              window.location.href="/internalservererror";
          if(error.status === 401)
              window.location.href = "/auth-failed";
          else
            window.location.href="/token-expired";
      }

    );
   
  }

  


}



