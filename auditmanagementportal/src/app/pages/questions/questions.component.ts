import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/classes/question';
import { QuestionsService } from 'src/app/services/questions.service';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css'],
  providers:[HomeComponent]
})
export class QuestionsComponent implements OnInit {

  public index:number = 1;
  
  constructor(private homeComponent: HomeComponent, private router: Router, private questionsService: QuestionsService) {
     this.questionsList = this.router.getCurrentNavigation()?.extras?.state?.questionsList;
     this.auditType = this.router.getCurrentNavigation()?.extras?.state?.auditType;
    console.log(this.questionsList)
    console.log(this.auditType)
    }


  ngOnInit(): void {
     
    window.onpopstate = function () { window.location.href = "/";
       localStorage.removeItem('token');
      localStorage.removeItem('userId');
  }
}
  
  onSubmit(){
   this.questionsService.submitResponse(this.questionsList).subscribe(
     (data: any)=>{console.log(data)
      
      this.router.navigateByUrl("/status");

     },
     (error)=>{
        window.location.href="/token-expired";
     }
     );
  }

  questionsList: any;
  auditType: any;




  
  

  
  
}


