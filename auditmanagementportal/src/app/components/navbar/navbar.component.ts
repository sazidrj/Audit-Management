import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public loggedIn = false;

  public user = localStorage.getItem('Username');

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
        this.loggedIn = this.loginService.isLoggedIn();
  }

  logoutUser(){
     this.loginService.logout();
     location.reload();
  }

}
