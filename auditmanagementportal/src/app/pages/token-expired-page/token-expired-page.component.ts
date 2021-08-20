import { LoginService } from 'src/app/services/login.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-token-expired-page',
  templateUrl: './token-expired-page.component.html',
  styleUrls: ['./token-expired-page.component.css']
})
export class TokenExpiredPageComponent implements OnInit {

  constructor(private router: Router, private loginService: LoginService) { }

  ngOnInit(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('userId')
 }

  loginAgain(){
   
    this.loginService.logout();
    window.location.href = "/";
  }
}
