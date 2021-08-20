import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-auth-failed',
  templateUrl: './auth-failed.component.html',
  styleUrls: ['./auth-failed.component.css']
})
export class AuthFailedComponent implements OnInit {

  constructor(private router: Router, private loginService: LoginService) { }

  ngOnInit(): void {
    localStorage.removeItem('token');
   localStorage.removeItem('userId');
  }

  loginAgain(){
    this.loginService.logout();
    window.location.href = "/";
  }

}
