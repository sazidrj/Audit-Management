import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

   userCredentials: any;

  url = 
"http://localhost:9092"
  constructor(private http:HttpClient) { }

  //calling the server to generate token
  generateToken(userCredentials: any){
    // generate token
    this.userCredentials = userCredentials;
    return this.http.post(`${this.url}/home`, userCredentials, {responseType:'text'});
  }

  


  sendUser(userCredentials: any){
     return this.http.post(`${this.url}/loginPage`, userCredentials);
  }

  // For login user 
  loginUser(token: string){
    localStorage.setItem("token", token);
    return true;
  }

  //to check that user is logged in
  isLoggedIn(){
    let token = localStorage.getItem("token");
    if(token=== undefined || token === '' || token === null){
       return false;
    }
    else{
      return true;
    }
  }

  //for logout the user
  logout(){
     localStorage.removeItem('token');
     localStorage.removeItem('Username');
     return true;
  }

  //for getting the token
  getToken(){
     return localStorage.getItem("token");
  }


}
