import { Component, OnInit } from '@angular/core';
import { StatusService } from 'src/app/services/status.service';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {

  constructor(private statusService: StatusService) { }
   
  statusObj : any;
  

  ngOnInit( ): void {
    this.statusService.getStatus().subscribe(
      (data:any)=>{
        this.statusObj = data;
        console.log(data);
        console.log(this.statusObj);
      },
      (error)=>{
          if(error.status===500)
              window.location.href="/internalservererror";
          else if(error.status === 401)
              window.location.href = "/auth-failed";
          else
              window.location.href="/token-expired";
      }
      );
      window.onpopstate = function () { window.location.href = "/";
       localStorage.removeItem('token');
      localStorage.removeItem('userId');
    }


  }


  newAudit(){
     window.location.href = "/home"
  }

  
}
