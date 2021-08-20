import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Question } from '../classes/question';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  url = "http://localhost:9092"
  header: any;

  constructor(private httpClient:HttpClient) { }

  temp =  JSON.stringify(localStorage.getItem('token'));

  headers_object = new HttpHeaders().set("Authorization", this.temp);

  
  getQuestions(auditType: any, projectDetails: any){

    let obj = {
        "auditType": auditType,
        "projectDetails" : projectDetails
       }
         
      return this.httpClient.post(`${this.url}/auditCheckListQuestions`, obj, {headers: this.headers_object })
 
      }

 
      
}

