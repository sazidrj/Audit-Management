import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-interal-server-error',
  templateUrl: './interal-server-error.component.html',
  styleUrls: ['./interal-server-error.component.css']
})
export class InteralServerErrorComponent implements OnInit {

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
