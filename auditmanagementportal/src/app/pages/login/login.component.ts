import { Component, NgZone, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2';
 

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userCredentials={
    userId:"",
    password:""
  }


  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
    this.loginService.logout();
  }

  response: any;
  invalidUser: any;

  onSubmit(){
     
     if(this.userCredentials.userId!='' && this.userCredentials.userId != null && this.userCredentials.password != '') {
       this.loginService.generateToken(this.userCredentials).subscribe(
        (data: any)=>{        
         this.loginService.loginUser(data);
         
         window.location.href="/home"
         localStorage.setItem("Username", this.userCredentials.userId)
         this.invalidUser = false;
        },
        (error)=>{     
          if(error.status === 404)    
            this.invalidUser = true; 
        }
        ); 
     }
     

  }

}
